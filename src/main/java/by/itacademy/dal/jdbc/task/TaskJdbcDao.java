package by.itacademy.dal.jdbc.task;

import by.itacademy.dal.TaskDao;
import by.itacademy.dal.jdbc.connector.Connector;
import by.itacademy.dal.jdbc.connector.HikariCPConnector;
import by.itacademy.exception.DaoException;
import by.itacademy.dal.jdbc.task.information.TaskInformationJdbcDao;
import by.itacademy.model.task.Task;
import by.itacademy.model.task.TaskInformation;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class TaskJdbcDao implements TaskDao {

    private final Connector connector;
    private final TaskInformationJdbcDao taskInformationJdbcDao;

    private static final String GET_BY_USER_ID = "SELECT task_id, user_id, task_information_id, dead_line, fixed, in_basket  FROM tasks WHERE user_id = ?;";
    private static final String GET_BY_ID_SQL = "SELECT task_id, user_id, task_information_id, dead_line, fixed, in_basket  FROM tasks WHERE task_id = ?;";
    private static final String GET_ALL_SQL = "SELECT task_id user_id, task_information_id, dead_line, fixed, in_basket  FROM tasks;";
    private static final String UPDATE_SQL = "UPDATE  tasks SET user_id = ?, task_information_id = ?, dead_line = ?, fixed = ?, in_basket = ? WHERE task_id = ?;";
    private static final String CREATE_SQL = "INSERT INTO tasks(user_id, task_information_id, dead_line, fixed, in_basket) VALUES(?,?,?,?,?);";
    private static final String DELETE_SQL = "DELETE FROM tasks WHERE task_id = ?;";

    {
        connector = HikariCPConnector.getInstance();
        taskInformationJdbcDao = TaskInformationJdbcDao.getInstance();
    }

    @Override
    public List<Task> findByUserId(int id) throws DaoException {
        try (Connection connection = connector.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(GET_BY_USER_ID);
            ps.setInt(1, id);
            List<Task> taskList = new ArrayList<>();
            try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        taskList.add(processResultSetMapping(rs));
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

    @Override
    public Task getById(int id) throws DaoException {
        try (Connection connection = connector.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(GET_BY_ID_SQL);
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return processResultSetMapping(rs);
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

    @Override
    public List<Task> getAll() throws DaoException {
        return null;
    }

    @Override
    public Task create(Task task) throws DaoException {
        try (Connection connection = connector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(CREATE_SQL, Statement.RETURN_GENERATED_KEYS);
            processStatementInitialization(statement, task);
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                task.setId(rs.getInt(1));
                task.setTaskInfo(taskInformationJdbcDao.create());
                return task;
            }

            throw new DaoException("Error generate ID for create entity: " + task);
        } catch (SQLException | DaoException e) {
            e.printStackTrace();
            throw new DaoException("Error process create entity: " + e.getMessage(), e);
        }
    }

    @Override
    public Task update(Task task) throws DaoException {
        try (Connection connection = connector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_SQL);
            processStatementInitialization(statement, task);
            statement.executeQuery();

            taskInformationJdbcDao.update(task.getTaskInfo());

            return task;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Error process update entity method: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(int id) throws DaoException {
        try (Connection connection = connector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_SQL);
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Error process delete entity method: " + e.getMessage());
        }
    }

    private Task processResultSetMapping(ResultSet rs) throws DaoException {
        try {
         //   'task_id', 'user_id', 'task_information_id', 'dead_line', 'fixed', 'in_basket'
            int taskInfoId = rs.getInt("task_information_id");
            TaskInformation taskInformation = taskInformationJdbcDao.getById(taskInfoId);
            return Task.builder()
                    .id(rs.getInt("task_id"))
                    .userId(rs.getInt("user_id"))
                    .taskInfo(taskInformation)
                    .deaLine(new Date(rs.getDate("dead_line").getTime()))
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
