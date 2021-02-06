package by.itacademy.dal.jdbc.user;

import by.itacademy.dal.UserDao;
import by.itacademy.dal.jdbc.connector.Connector;
import by.itacademy.dal.jdbc.connector.HikariCPConnector;
import by.itacademy.exception.DaoException;
import by.itacademy.dal.jdbc.user.metadata.UserMetaData;
import by.itacademy.exception.UsernameNotFoundException;
import by.itacademy.model.user.Credential;
import by.itacademy.model.user.PersonalInformation;
import by.itacademy.model.user.Role;
import by.itacademy.model.user.User;
import by.itacademy.security.UserDetails;

import java.sql.*;
import java.util.List;



public class UserJdbcDao implements UserDao {

    private final Connector connector;
    private final CredentialsJdbcDao credentialsJdbcDao;
    private final RolesMapJdbcDao rolesMapJdbcDao;
    private final PersonalInformationJdbcDao personalInformationJdbcDao;

    private static UserJdbcDao instance = null;

    private static final String GET_BY_ID_SQL = "SELECT user_id, credentials_id, personal_information_id, profile_enable  FROM users WHERE user_id = ?;";
    private static final String GET_ALL_SQL = "SELECT user_id, credentials_id, personal_information_id, profile_enable   FROM users;";
    private static final String UPDATE_SQL = "UPDATE  users SET profile_enable  WHERE user_id = ?;";
    private static final String CREATE_SQL = "INSERT INTO users(SELECT credentials_id, personal_information_id, profile_enable) VALUES(?,?,?);";
    private static final String DELETE_SQL = "DELETE FROM users, WHERE user_id = ?;";
    private static final String GET_BY_NAME_SQL = "SELECT user_id, credentials_id, personal_information_id, profile_enable  FROM users WHERE credentials_id = ?;";

    {
        connector = HikariCPConnector.getInstance();
        credentialsJdbcDao = CredentialsJdbcDao.getInstance();
        rolesMapJdbcDao = RolesMapJdbcDao.getInstance();
        personalInformationJdbcDao = PersonalInformationJdbcDao.getInstance();
    }

    @Override
    public User getById(int id) throws DaoException {
        User user;
        try (Connection connection = connector.getConnection()) {
            try {
                user = processResultSetMappingForUser(getUserMetaDataById(id, connection), connection);
                connection.commit();

            } catch (DaoException e) {
                connection.rollback();
                throw new DaoException(e);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Error receive database connection: " + e.getMessage(), e);
        }
        return user;
    }

    @Override
    public User create(User user) throws DaoException {
        try (Connection connection = connector.getConnection()) {
            try {
                user = createUser(user, connection);
                connection.commit();

            } catch (DaoException e) {
                connection.rollback();
                throw new DaoException(e);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Error receive database connection: " + e.getMessage(), e);
        }
        return user;
    }

    @Override
    public User update(User user) throws DaoException {
        try (Connection connection = connector.getConnection()) {
            try {
                user = updateUser(user, connection);
                connection.commit();

            } catch (DaoException e) {
                connection.rollback();
                throw new DaoException(e);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Error receive database connection: " + e.getMessage(), e);
        }
        return user;
    }

    @Override
    public void delete(int id) throws DaoException {
        try (Connection connection = connector.getConnection()) {
            try {
                deleteUser(id, connection);
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
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        UserDetails userDetails;
        try (Connection connection = connector.getConnection()) {
            try {
                userDetails = getUserByName(name, connection);
                connection.commit();

            } catch (DaoException e) {
                connection.rollback();
                throw new UsernameNotFoundException(e);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new UsernameNotFoundException("Error receive database connection: " + e.getMessage(), e);
        }
        return userDetails;
    }

    private UserMetaData getUserMetaDataById(int id, Connection connection) throws DaoException {
        try {
            PreparedStatement ps = connection.prepareStatement(GET_BY_ID_SQL);
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return processResultSetMappingForUserMetaData(rs);
                }
                throw new DaoException("Invalid entity id: " + id);

            } catch (SQLException e) {
                throw new DaoException("Error process getById entity method: " + e.getMessage(), e);
            }
        } catch (SQLException e) {
            throw new DaoException("Error receive database connection: " + e.getMessage(), e);
        }
    }


    public List<User> getAll() throws DaoException {
        return null;
    }



    private User createUser(User user, Connection connection) throws DaoException {
        try {

            PreparedStatement statement = connection.prepareStatement(CREATE_SQL, Statement.RETURN_GENERATED_KEYS);

            Credential credential = credentialsJdbcDao.create(user.getCredential(), connection);
            PersonalInformation personalInformation = personalInformationJdbcDao.create(user.getPersonalInformation(), connection);
            List<Role> roleList = rolesMapJdbcDao.grantAuthoritiesToUser(user.getId(), (List<Role>) user.getAuthorities(), connection);

            processStatementInitialization(statement, user);
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                return User.builder()
                        .id(rs.getInt("user_id"))
                        .credential(credential)
                        .personalInformation(personalInformation)
                        .roles(roleList)
                        .accountNotLocked(true)
                        .build();
            }

            throw new DaoException("Error generate ID for create entity: " + user);
        } catch (SQLException | DaoException e) {
            throw new DaoException("Error process create entity: " + e.getMessage(), e);
        }
    }

    private User updateUser(User user, Connection connection) throws DaoException {
        try {

            PreparedStatement statement = connection.prepareStatement(UPDATE_SQL);
            processStatementInitialization(statement, user);
            statement.executeQuery();

            credentialsJdbcDao.update(user.getCredential(), connection);
            personalInformationJdbcDao.update(user.getPersonalInformation(), connection);

            return user;

        } catch (SQLException e) {
            throw new DaoException("Error process update entity method: " + e.getMessage(), e);
        }
    }


    private void deleteUser(int id, Connection connection) throws DaoException {
        try {

            PreparedStatement statement = connection.prepareStatement(DELETE_SQL);

            UserMetaData oldUser = getUserMetaDataById(id, connection);

            statement.setInt(1, id);
            statement.executeUpdate();

            credentialsJdbcDao.delete(oldUser.getCredentialsId(), connection);
            personalInformationJdbcDao.delete(oldUser.getPersonalInformationId(), connection);
            rolesMapJdbcDao.delete(oldUser.getId(), connection);

        } catch (SQLException e) {
            throw new DaoException("Error process delete entity method: " + e.getMessage());
        }
    }


    private User getUserByName(String name, Connection connection) throws DaoException {
        Credential credential = credentialsJdbcDao.getByLogin(name, connection);
        UserMetaData userMetaData = getUserMetaDataByCredentialsId(credential.getId(), connection);
        return processResultSetMappingForUser(userMetaData, connection);
    }

    private UserMetaData getUserMetaDataByCredentialsId(int id, Connection connection) throws DaoException {
        try {
            PreparedStatement ps = connection.prepareStatement(GET_BY_NAME_SQL);
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return processResultSetMappingForUserMetaData(rs);
                }
                throw new DaoException("Invalid credentials_id: " + id);

            } catch (SQLException e) {
                throw new DaoException("Error process getUserMetaDataByCredentialsId method: " + e.getMessage(), e);
            }
        } catch (SQLException e) {
            throw new DaoException("Error receive database connection: " + e.getMessage(), e);
        }
    }

    public static UserJdbcDao getInstance() {
        if (instance == null) {
            instance = new UserJdbcDao();
        }
        return instance;
    }

    private User processResultSetMappingForUser(UserMetaData userMetaData, Connection connection) throws DaoException {

        Credential credential = credentialsJdbcDao.getById(userMetaData.getCredentialsId(), connection);
        List<Role> roleList = rolesMapJdbcDao.getByUserId(userMetaData.getId(), connection);
        PersonalInformation personalInformation = personalInformationJdbcDao.getById(userMetaData.getPersonalInformationId(), connection);

        return processResultSetMappingForUser(userMetaData, credential, roleList, personalInformation);
    }

    private User processResultSetMappingForUser(UserMetaData userMetaData, Credential credential, List<Role> roleList,
                                                PersonalInformation personalInformation) {
        int id;
        boolean profileEnable;

        id = userMetaData.getId();
        profileEnable = userMetaData.isAccountNotLocked();

        return User.builder()
                .id(id)
                .credential(credential)
                .roles(roleList)
                .personalInformation(personalInformation)
                .accountNotLocked(profileEnable)
                .build();
    }

    private UserMetaData processResultSetMappingForUserMetaData(ResultSet rs) throws SQLException {
        return UserMetaData.builder()
                .id(rs.getInt("user_id"))
                .credentialsId(rs.getInt("credentials_id"))
                .personalInformationId(rs.getInt("personal_information_id"))
                .accountNotLocked(rs.getBoolean("profile_enable"))
                .build();
    }

    private void processStatementInitialization(PreparedStatement ps, User user) throws SQLException {
        ps.setBoolean(1, user.isAccountNonLocked());
    }
}
