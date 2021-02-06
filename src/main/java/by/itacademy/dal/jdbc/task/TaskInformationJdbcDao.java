package by.itacademy.dal.jdbc.task;

import by.itacademy.exception.DaoException;
import by.itacademy.model.task.TaskInformation;

import java.sql.*;


public class TaskInformationJdbcDao {

    private static TaskInformationJdbcDao instance = null;


    private static final String GET_BY_ID_SQL = "SELECT task_information_id, description, file_path  FROM task_information WHERE task_information_id = ?;";
    private static final String UPDATE_SQL = "UPDATE  task_information SET description = ?, file_path = ? WHERE task_information_id = ?;";
    private static final String CREATE_SQL = "INSERT INTO task_information(description, file_path) VALUES(?,?);";
    private static final String DELETE_SQL = "DELETE FROM task_information WHERE task_information_id = ?;";

    public TaskInformation getById(int id, Connection connection) throws DaoException {
        try {
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


    public TaskInformation create(TaskInformation taskInformation, Connection connection) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(CREATE_SQL, Statement.RETURN_GENERATED_KEYS);
            processStatementInitialization(statement, taskInformation);
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                taskInformation.setId(rs.getInt(1));
                return taskInformation;
            }

            throw new DaoException("Error generate ID for create entity: " + taskInformation);
        } catch (SQLException | DaoException e) {
            e.printStackTrace();
            throw new DaoException("Error process create entity: " + e.getMessage(), e);
        }
    }


    public TaskInformation update(TaskInformation taskInformation, Connection connection) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(UPDATE_SQL);
            processStatementInitialization(statement, taskInformation);
            statement.setInt(3, taskInformation.getId());
            statement.execute();

            return taskInformation;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Error process update entity method: " + e.getMessage(), e);
        }
    }


    public void delete(int id, Connection connection) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_SQL);
            statement.setInt(1, id);
            statement.execute();

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
            String filePath = rs.getString("file_path");

            return new TaskInformation(id, description, filePath);
        } catch (SQLException e) {
            throw new DaoException("Error mapping resultSet to TaskInformation Object");
        }
    }

    private void processStatementInitialization(PreparedStatement ps, TaskInformation taskInformation) throws SQLException {
        ps.setString(1, taskInformation.getDescription());
        ps.setString(2, taskInformation.getFilepath());
    }
}