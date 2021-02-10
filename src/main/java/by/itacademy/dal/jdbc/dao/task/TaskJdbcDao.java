package by.itacademy.dal.jdbc.dao.task;

import by.itacademy.dal.TaskDao;
import by.itacademy.dal.jdbc.AbstractBasicCrudJdbcDao;
import by.itacademy.dal.jdbc.connector.Connector;
import by.itacademy.dal.jdbc.query.task.TaskJdbcSqlQueryHolder;
import by.itacademy.exception.DaoException;
import by.itacademy.model.task.Task;
import by.itacademy.model.task.TaskInformation;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskJdbcDao extends AbstractBasicCrudJdbcDao<Task> implements TaskDao {

    private final TaskInformationJdbcDao taskInformationJdbcDao;

    public TaskJdbcDao(Connector connector, TaskInformationJdbcDao taskInformationJdbcDao) {
        super(connector);
        this.taskInformationJdbcDao = taskInformationJdbcDao;
    }

    @Override
    public List<Task> getByUserId(int id) throws DaoException {
        List<Task> taskList;
        try (Connection connection = getConnector().getConnection()) {
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
    public void deleteByUserId(int userId) throws DaoException {
        try (Connection connection = getConnector().getConnection()) {
            try {
                PreparedStatement statement = connection.prepareStatement(getSqlHolder().getDeleteByUserIdSql());
                statement.setInt(1, userId);
                int i = statement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
                throw new DaoException("Error process delete entity method: " + e.getMessage());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Error receive database connection: " + e.getMessage(), e);
        }
    }

    private List<Task> findTaskByUserId(int id, Connection connection) throws DaoException {
        try {
            PreparedStatement ps = connection.prepareStatement(getSqlHolder().getGetByUserId());
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


    protected Task getEntityById(int id, Connection connection) throws DaoException {
        try {
            PreparedStatement ps = connection.prepareStatement(getSqlHolder().getByIdSql());
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

    protected Task createEntity(Task task, Connection connection) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(getSqlHolder().createSql(), Statement.RETURN_GENERATED_KEYS);
            processStatementInitialization(statement, task);
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                task.setId(rs.getInt(1));
                task.setTaskInfo(taskInformationJdbcDao.create(task.getTaskInfo()));
                return task;
            }

            throw new DaoException("Error generate ID for create entity: " + task);
        } catch (SQLException | DaoException e) {
            e.printStackTrace();
            throw new DaoException("Error process create entity: " + e.getMessage(), e);
        }
    }

    protected Task updateEntity(Task task, Connection connection) throws DaoException {
        try {

            PreparedStatement statement = connection.prepareStatement(getSqlHolder().updateSql());
            processStatementInitialization(statement, task);
            statement.executeQuery();

            taskInformationJdbcDao.update(task.getTaskInfo());

            return task;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Error process update entity method: " + e.getMessage(), e);
        }
    }

    protected void deleteEntity(int id, Connection connection) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(getSqlHolder().deleteSql());
            statement.setInt(1, id);
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Error process delete entity method: " + e.getMessage());
        }
    }

    private TaskJdbcSqlQueryHolder getSqlHolder() {
        return new TaskJdbcSqlQueryHolder();
    }

    private Task processResultSetMapping(ResultSet rs, Connection connection) throws DaoException {
        try {
            int taskInfoId = rs.getInt("task_information_id");
            TaskInformation taskInformation = taskInformationJdbcDao.getById(taskInfoId);
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
