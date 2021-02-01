package by.itacademy.dal.jdbc.task.information;

import by.itacademy.dal.jdbc.connector.Connector;
import by.itacademy.dal.jdbc.connector.HikariCPConnector;
import by.itacademy.dal.jdbc.exception.DaoException;
import by.itacademy.model.task.TaskInformation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class TaskInformationJdbcDao {

    private final Connector connector;

    private static TaskInformationJdbcDao instance = null;


    private static final String GET_BY_ID_SQL = "SELECT 'task_information_id', 'description', 'filepath'  FROM task_information, WHERE 'task_information_id' = ?;";
    private static final String UPDATE_SQL = "UPDATE  task_information SET 'description' = ?, 'filepath' = ?, WHERE 'task_information_id' = ?;";
    private static final String CREATE_SQL = "INSERT INTO task_information('description', 'filepath') VALUES(?,?);";
    private static final String DELETE_SQL = "DELETE FROM task_information, WHERE 'task_information_id' = ?;";

    {
        connector = HikariCPConnector.getInstance();
    }


    public TaskInformation getById(int id) throws DaoException {
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


    public TaskInformation create(TaskInformation taskInformation) throws DaoException {
        try (Connection connection = connector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(CREATE_SQL);
            processStatementInitialization(statement, taskInformation);
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                return getById(rs.getInt(1));
            }

            throw new DaoException("Error generate ID for create entity: " + taskInformation);
        } catch (SQLException | DaoException e) {
            e.printStackTrace();
            throw new DaoException("Error process create entity: " + e.getMessage(), e);
        }
    }


    public TaskInformation update(TaskInformation taskInformation) throws DaoException {
        try (Connection connection = connector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_SQL);
            processStatementInitialization(statement, taskInformation);
            statement.executeQuery();

            return taskInformation;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Error process update entity method: " + e.getMessage(), e);
        }
    }


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

    public static TaskInformationJdbcDao getInstance() {
        if (instance == null) {
            instance = new TaskInformationJdbcDao();
        }
        return instance;
    }

    private TaskInformation processResultSetMapping(ResultSet rs) throws DaoException {
        try {
            int id = rs.getInt("task_information_id");
            String description = rs.getString("description");
            String filepath = rs.getString("filepath");

            return new TaskInformation(id, description, filepath);
        } catch (SQLException e) {
            throw new DaoException("Error mapping resultSet to TaskInformation Object");
        }
    }

    private void processStatementInitialization(PreparedStatement ps, TaskInformation taskInformation) throws SQLException {
        ps.setString(1, taskInformation.getDescription());
        ps.setString(2, taskInformation.getFilepath());
    }
}