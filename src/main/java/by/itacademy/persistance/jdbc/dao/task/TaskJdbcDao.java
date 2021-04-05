package by.itacademy.persistance.jdbc.dao.task;

import by.itacademy.persistance.TaskDao;
import by.itacademy.persistance.jdbc.AbstractBasicCrudJdbcDao;
import by.itacademy.persistance.jdbc.connector.Connector;
import by.itacademy.persistance.jdbc.query.task.TaskJdbcSqlQueryHolder;
import by.itacademy.exception.dao.DaoException;
import by.itacademy.model.task.Task;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Log4j2

public class TaskJdbcDao extends AbstractBasicCrudJdbcDao<Task> implements TaskDao {

    private static TaskJdbcDao instance;

    public TaskJdbcDao(Connector connector) {
        super(connector);
        instance = this;
    }

    @Override
    public List<Task> getByUserId(int id) throws DaoException {
        List<Task> taskList;
        try (Connection connection = getConnector().getConnection()) {
            try {
                taskList = findTaskByUserId(id, connection);

            } catch (DaoException e) {
                throw new DaoException(e);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            String message = "Error receive database connection: ";
            log.debug(message, Arrays.toString(e.getStackTrace()));
            throw new DaoException(message + e.getMessage(), e);
        }
        return taskList;
    }

    @Override
    public void deleteByUserId(int userId) throws DaoException {
        try (Connection connection = getConnector().getConnection()) {
            try {
                PreparedStatement statement = connection.prepareStatement(getSqlHolder().getDeleteByUserIdSql());
                statement.setInt(1, userId);

            } catch (SQLException e) {
                e.printStackTrace();
                throw new DaoException("Error process delete entity method: " + e.getMessage());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            String message = "Error receive database connection: ";
            log.debug(message, Arrays.toString(e.getStackTrace()));
            throw new DaoException(message + e.getMessage(), e);
        }
    }

    private List<Task> findTaskByUserId(int id, Connection connection) throws DaoException {
        try {
            PreparedStatement ps = connection.prepareStatement(getSqlHolder().getGetByUserId());
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
            throw new DaoException("Error initialize prepared statement: " + e.getMessage(), e);
        }
    }

    @Override
    protected Task getEntityById(int id, Connection connection) throws DaoException {
        try {
            PreparedStatement ps = connection.prepareStatement(getSqlHolder().getByIdSql());
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
            throw new DaoException("Error initialize prepared statement: " + e.getMessage(), e);
        }
    }

    @Override
    protected Task createEntity(Task task, Connection connection) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(getSqlHolder().createSql(), Statement.RETURN_GENERATED_KEYS);
            processStatementInitialization(statement, task);
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                task.setId(rs.getInt(1));
                return task;
            }

            throw new DaoException("Error generate ID for create entity: " + task);
        } catch (SQLException | DaoException e) {
            e.printStackTrace();
            throw new DaoException("Error process create entity: " + e.getMessage(), e);
        }
    }

    @Override
    protected Task updateEntity(Task task, Connection connection) throws DaoException {
        try {

            PreparedStatement statement = connection.prepareStatement(getSqlHolder().updateSql());
            statement.setInt(7,task.getId());
            processStatementInitialization(statement, task);
            statement.executeUpdate();
            return task;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Error process update entity method: " + e.getMessage(), e);
        }
    }

    @Override
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

    private Task processResultSetMapping(ResultSet rs) throws DaoException {
        try {
            return Task.builder()
                    .id(rs.getInt("task_id"))
                    .userId(rs.getInt("user_id"))
                    .description(rs.getString("description"))
                    .name(rs.getString("task_name"))
                    .deadLine(new Date(rs.getDate("deadline").getTime()))
                    .fixed(rs.getBoolean("fixed"))
                    .inBasket(rs.getBoolean("in_basket"))
                    .build();
        } catch (SQLException e) {
            throw new DaoException("Error mapping resultSet to Task Object");
        }
    }

    private void processStatementInitialization(PreparedStatement ps, Task task) throws SQLException {
        ps.setInt(1, task.getUserId());
        ps.setString(2, task.getName());
        ps.setString(3, task.getDescription());
        ps.setDate(4, new java.sql.Date(task.getDeadLine().getTime()));
        ps.setBoolean(5, task.isFixed());
        ps.setBoolean(6, task.isInBasket());
    }

    public static TaskJdbcDao getInstance() {
        return instance;
    }
}
