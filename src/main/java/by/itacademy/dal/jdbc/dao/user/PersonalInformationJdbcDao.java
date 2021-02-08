package by.itacademy.dal.jdbc.dao.user;

import by.itacademy.exception.DaoException;
import by.itacademy.model.user.PersonalInformation;

import java.sql.*;

public class PersonalInformationJdbcDao {

    private static PersonalInformationJdbcDao instance = null;

    private static final String GET_BY_ID_SQL = "SELECT personal_information_id, first_name, last_name  FROM personal_information WHERE personal_information_id = ?;";
    private static final String UPDATE_SQL = "UPDATE  personal_information SET first_name = ?, last_name = ? WHERE personal_information_id = ?;";
    private static final String CREATE_SQL = "INSERT INTO personal_information(first_name, last_name) VALUES(?,?);";
    private static final String DELETE_SQL = "DELETE FROM personal_information WHERE personal_information_id = ?;";

    public PersonalInformation getById(int id, Connection connection) throws DaoException {
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

    public PersonalInformation create(PersonalInformation personalInformation, Connection connection) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(CREATE_SQL, Statement.RETURN_GENERATED_KEYS);
            processStatementInitialization(statement, personalInformation);
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                personalInformation.setId(rs.getInt(1));
                return personalInformation;
            }

            throw new DaoException("Error generate ID for create entity: " + personalInformation);
        } catch (SQLException | DaoException e) {
            e.printStackTrace();
            throw new DaoException("Error process create entity: " + e.getMessage(), e);
        }
    }

    public PersonalInformation update(PersonalInformation personalInformation, Connection connection) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(UPDATE_SQL, Statement.RETURN_GENERATED_KEYS);
            processStatementInitialization(statement, personalInformation);
            statement.setInt(3,personalInformation.getId());
            statement.execute();

            return personalInformation;

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

    public static PersonalInformationJdbcDao getInstance() {
        if (instance == null) {
            instance = new PersonalInformationJdbcDao();
        }
        return instance;
    }
    
    private PersonalInformation processResultSetMapping(ResultSet rs) throws DaoException {
        try {
            int id = rs.getInt("personal_information_id");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");

            return new PersonalInformation(id, firstName, lastName);
        } catch (SQLException e) {
            throw new DaoException("Error mapping resultSet to personal_information Object");
        }
    }

    private void processStatementInitialization(PreparedStatement ps, PersonalInformation personalInformation) throws SQLException {
        ps.setString(1, personalInformation.getFirstName());
        ps.setString(2, personalInformation.getLastName());
    }
}
