package by.itacademy.dal.jdbc.dao.task;

import by.itacademy.dal.TaskDao;
import by.itacademy.dal.jdbc.connector.Connector;
import by.itacademy.exception.DaoException;
import by.itacademy.model.task.Task;
import by.itacademy.model.task.TaskInformation;
import lombok.RequiredArgsConstructor;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
public class TaskJdbcDao implements TaskDao {

    private final Connector connector;
    private final TaskInformationJdbcDao taskInformationJdbcDao;

    private static final String GET_BY_USER_ID = "SELECT task_id, user_id, task_information_id, deadline, fixed, in_basket  FROM tasks WHERE user_id = ?;";
    private static final String GET_BY_ID_SQL = "SELECT task_id, user_id, task_information_id, deadline, fixed, in_basket  FROM tasks WHERE task_id = ?;";
//    private static final String GET_ALL_SQL = "SELECT task_id user_id, task_information_id, deadline, fixed, in_basket  FROM tasks;";
    private static final String UPDATE_SQL = "UPDATE  tasks SET user_id = ?, task_information_id = ?, deadline = ?, fixed = ?, in_basket = ? WHERE task_id = ?;";
    private static final String CREATE_SQL = "INSERT INTO tasks(user_id, task_information_id, deadline, fixed, in_basket) VALUES(?,?,?,?,?);";
    private static final String DELETE_SQL = "DELETE FROM tasks WHERE task_id = ?;";
    private static final String DELETE_BY_USER_ID_SQL = "DELETE FROM tasks WHERE user_id = ?;";

    @Override
    public List<Task> getByUserId(int id) throws DaoException {
        List<Task> taskList;
        try (Connection connection = connector.getConnection()) {
            try {
                taskList = findTaskByUserId(id, connection);
                connection.commit();

            } catch (DaoException e) {
                connection.rollback();
                throw new DaoException(e);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Error receive database connection: " + e.getMessage(), e);
        }
        return taskList;
    }

    @Override
    public Task getById(int id) throws DaoException {
        Task task;
        try (Connection connection = connector.getConnection()) {
            try {
                task = getTaskById(id, connection);
                connection.commit();

            } catch (DaoException e) {
                connection.rollback();
                throw new DaoException(e);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Error receive database connection: " + e.getMessage(), e);
        }
        return task;
    }


    @Override
    public Task create(Task task) throws DaoException {
        try (Connection connection = connector.getConnection()) {
            try {
                createTask(task, connection);
                connection.commit();

            } catch (DaoException e) {
                connection.rollback();
                throw new DaoException(e);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Error receive database connection: " + e.getMessage(), e);
        }
        return task;
    }

    @Override
    public Task update(Task task) throws DaoException {
        try (Connection connection = connector.getConnection()) {
            try {
                updateTask(task, connection);
                connection.commit();

            } catch (DaoException e) {
                connection.rollback();
                throw new DaoException(e);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Error receive database connection: " + e.getMessage(), e);
        }
        return task;
    }

    @Override
    public void delete(int id) throws DaoException {
        try (Connection connection = connector.getConnection()) {
            try {
                deleteTask(id, connection);
                connection.commit();

            } catch (DaoException e) {
                connection.rollback();
                throw new DaoException(e);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Error receive database connection: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteByUserId(int userId, Connection connection) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_BY_USER_ID_SQL);
            statement.setInt(1, userId);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Error process delete entity method: " + e.getMessage());
        }
    }

    private List<Task> findTaskByUserId(int id, Connection connection) throws DaoException {
        try {
            PreparedStatement ps = connection.prepareStatement(GET_BY_USER_ID);
            ps.setInt(1, id);
            List<Task> taskList = new ArrayList<>();
            try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        taskList.add(processResultSetMapping(rs, connection));
                    }
                    return taskList;

            } catch (SQLException e) {
                e.printStackTrace();
                throw new DaoException("Error process getById entity method: " + e.getMessage(), e);
            }
        } catch (SQLException e) {
            throw new DaoException("Error receive database connection: " + e.getMessage(), e);
        }
    }


    private Task getTaskById(int id, Connection connection) throws DaoException {
        try {
            PreparedStatement ps = connection.prepareStatement(GET_BY_ID_SQL);
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return processResultSetMapping(rs, connection);
                }
                throw new DaoException("Invalid entity id: " + id);

            } catch (SQLException e) {
                e.printStackTrace();
                throw new DaoException("Error process getById entity method: " + e.getMessage(), e);
            }
        } catch (SQLException e) {
            throw new DaoException("Error receive database connection: " + e.getMessage(), e);
        }
    }

    private Task createTask(Task task, Connection connection) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(CREATE_SQL, Statement.RETURN_GENERATED_KEYS);
            processStatementInitialization(statement, task);
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                task.setId(rs.getInt(1));
                task.setTaskInfo(taskInformationJdbcDao.create(task.getTaskInfo(), connection));
                return task;
            }

            throw new DaoException("Error generate ID for create entity: " + task);
        } catch (SQLException | DaoException e) {
            e.printStackTrace();
            throw new DaoException("Error process create entity: " + e.getMessage(), e);
        }
    }

    private Task updateTask(Task task, Connection connection) throws DaoException {
        try {

            PreparedStatement statement = connection.prepareStatement(UPDATE_SQL);
            processStatementInitialization(statement, task);
            statement.executeQuery();

            taskInformationJdbcDao.update(task.getTaskInfo(), connection);

            return task;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Error process update entity method: " + e.getMessage(), e);
        }
    }

    private void deleteTask(int id, Connection connection) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_SQL);
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Error process delete entity method: " + e.getMessage());
        }
    }

    private Task processResultSetMapping(ResultSet rs, Connection connection) throws DaoException {
        try {
            int taskInfoId = rs.getInt("task_information_id");
            TaskInformation taskInformation = taskInformationJdbcDao.getById(taskInfoId, connection);
            return Task.builder()
                    .id(rs.getInt("task_id"))
                    .userId(rs.getInt("user_id"))
                    .taskInfo(taskInformation)
                    .deaLine(new Date(rs.getDate("deadline").getTime()))
                    .fixed(rs.getBoolean("fixed"))
                    .inBasket(rs.getBoolean("in_basket"))
                    .build();
        } catch (SQLException e) {
            throw new DaoException("Error mapping resultSet to Task Object");
        }
    }

    private void processStatementInitialization(PreparedStatement ps, Task task) throws SQLException {
        ps.setInt(1, task.getUserId());
        ps.setInt(2, task.getTaskInfo().getId());
        ps.setDate(3, new java.sql.Date(task.getDeaLine().getTime()));
        ps.setBoolean(4, task.isFixed());
        ps.setBoolean(5, task.isInBasket());
    }
}
