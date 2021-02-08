package by.itacademy.dal.jdbc.dao.user;

import by.itacademy.exception.DaoException;
import by.itacademy.model.user.Credential;

import java.sql.*;

public class CredentialsJdbcDao {

    private static CredentialsJdbcDao instance = null;

    private static final String GET_BY_ID_SQL = "SELECT credentials_id, login, password  FROM credentials WHERE credentials_id = ?;";
    private static final String GET_BY_LOGIN_SQL = "SELECT credentials_id, login, password  FROM credentials WHERE login = ?;";
    private static final String UPDATE_SQL = "UPDATE  credentials SET password = ? WHERE credentials_id = ?;";
    private static final String CREATE_SQL = "INSERT INTO credentials(login, password) VALUES(?,?);";
    private static final String DELETE_SQL = "DELETE FROM credentials WHERE credentials_id = ?;";

    public Credential getById(int id, Connection connection) throws DaoException {
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

    public Credential getByLogin(String login, Connection connection) throws DaoException {
        try {
            PreparedStatement ps = connection.prepareStatement(GET_BY_LOGIN_SQL);
            ps.setString(1, login);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return processResultSetMapping(rs);
                }
                throw new DaoException("Invalid login: " + login);

            } catch (SQLException e) {
                e.printStackTrace();
                throw new DaoException("Error process getById entity method: " + e.getMessage(), e);
            }
        } catch (SQLException e) {
            throw new DaoException("Error receive database connection: " + e.getMessage(), e);
        }
    }


    public Credential create(Credential credential, Connection connection) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(CREATE_SQL, Statement.RETURN_GENERATED_KEYS);
            processStatementInitialization(statement, credential);
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                credential.setId(rs.getInt(1));
                return credential;
            }

            throw new DaoException("Error generate ID for create entity: " + credential);
        } catch (SQLException | DaoException e) {
            e.printStackTrace();
            throw new DaoException("Error process create entity: " + e.getMessage(), e);
        }
    }


    public Credential update(Credential credential, Connection connection) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(UPDATE_SQL);
            statement.setString(1, credential.getPassword());
            statement.setInt(2, credential.getId());
            statement.execute();

            return credential;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Error process update entity method: " + e.getMessage(), e);
        }
    }


    public void delete(int id, Connection connection) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_SQL);
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Error process delete entity method: " + e.getMessage());
        }
    }

    public static CredentialsJdbcDao getInstance() {
        if (instance == null) {
            instance = new CredentialsJdbcDao();
        }
        return instance;
    }

    private Credential processResultSetMapping(ResultSet rs) throws DaoException {
        try {
            int id = rs.getInt("credentials_id");
            String login = rs.getString("login");
            String password = rs.getString("password");

            return new Credential(id, login, password);
        } catch (SQLException e) {
            throw new DaoException("Error mapping resultSet to Credentials Object");
        }
    }

    private void processStatementInitialization(PreparedStatement ps, Credential credential) throws SQLException {
        ps.setString(1, credential.getLogin());
        ps.setString(2, credential.getPassword());
    }
}
