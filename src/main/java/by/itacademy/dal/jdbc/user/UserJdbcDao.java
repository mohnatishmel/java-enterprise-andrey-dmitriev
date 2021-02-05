package by.itacademy.dal.jdbc.user;

import by.itacademy.dal.UserDao;
import by.itacademy.dal.jdbc.connector.Connector;
import by.itacademy.dal.jdbc.connector.HikariCPConnector;
import by.itacademy.exception.DaoException;
import by.itacademy.dal.jdbc.user.metadata.UserMetaData;
import by.itacademy.exception.UsernameNotFoundException;
import by.itacademy.model.user.Credentials;
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

    private static final String GET_BY_ID_SQL = "SELECT 'user_id', 'credentials_id', 'personal_information_id', `profile_enable`  FROM 'users', WHERE 'user_id' = ?;";
    private static final String GET_ALL_SQL = "SELECT 'user_id', 'credentials_id', 'personal_information_id', `profile_enable`   FROM 'users';";
    private static final String UPDATE_SQL = "UPDATE  users SET `profile_enable` , WHERE 'user_id' = ?;";
    private static final String CREATE_SQL = "INSERT INTO users(SELECT 'credentials_id', 'personal_information_id', `profile_enable`) VALUES(?,?,?);";
    private static final String DELETE_SQL = "DELETE FROM users, WHERE 'user_id' = ?;";
    private static final String GET_BY_NAME_SQL = "SELECT 'user_id', 'credentials_id', 'personal_information_id', `profile_enable`  FROM 'users', WHERE 'credentials_id' = ?;";

    {
        connector = HikariCPConnector.getInstance();
        credentialsJdbcDao = CredentialsJdbcDao.getInstance();
        rolesMapJdbcDao = RolesMapJdbcDao.getInstance();
        personalInformationJdbcDao = PersonalInformationJdbcDao.getInstance();
    }


    public User getById(int id) throws DaoException {
        return processResultSetMappingForUser(getUserMetaDataById(id));
    }

    private UserMetaData getUserMetaDataById(int id) throws DaoException {
        try (Connection connection = connector.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(GET_BY_ID_SQL);
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return processResultSetMappingForUserMetaData(rs);
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


    public List<User> getAll() throws DaoException {
        return null;
    }

    public User create(User user) throws DaoException {
        try (Connection connection = connector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(CREATE_SQL, Statement.RETURN_GENERATED_KEYS);

            Credentials credentials = credentialsJdbcDao.create(user.getCredentials());
            PersonalInformation personalInformation = personalInformationJdbcDao.create(user.getPersonalInformation());
            List<Role> roleList = rolesMapJdbcDao.grantAuthoritiesToUser(user.getId(), (List<Role>) user.getAuthorities());

            processStatementInitialization(statement, user);
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                return  User.builder()
                        .id(rs.getInt("user_id"))
                        .credentials(credentials)
                        .personalInformation(personalInformation)
                        .roles(roleList)
                        .accountNotLocked(true)
                        .build();
            }

            throw new DaoException("Error generate ID for create entity: " + user);
        } catch (SQLException | DaoException e) {
            e.printStackTrace();
            throw new DaoException("Error process create entity: " + e.getMessage(), e);
        }
    }

    public User update(User user) throws DaoException {
        try (Connection connection = connector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_SQL);
            processStatementInitialization(statement, user);
            statement.executeQuery();

            credentialsJdbcDao.update(user.getCredentials());
            personalInformationJdbcDao.update(user.getPersonalInformation());

            return user;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Error process update entity method: " + e.getMessage(), e);
        }
    }


    public void delete(int id) throws DaoException {
        try (Connection connection = connector.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_SQL);

            UserMetaData oldUser = getUserMetaDataById(id);

            statement.setInt(1, id);
            statement.executeUpdate();

            credentialsJdbcDao.delete(oldUser.getCredentialsId());
            personalInformationJdbcDao.delete(oldUser.getPersonalInformationId());
            rolesMapJdbcDao.delete(oldUser.getId());

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Error process delete entity method: " + e.getMessage());
        }
    }


    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        try {
            return processResultSetMappingForUser(getUserMetaDataByName(name));
        } catch (DaoException e) {
            e.printStackTrace();
            throw new UsernameNotFoundException(e);
        }
    }

    private UserMetaData getUserMetaDataByName(String name) throws DaoException {
        try (Connection connection = connector.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(GET_BY_NAME_SQL);
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return processResultSetMappingForUserMetaData(rs);
                }
                throw new DaoException("Invalid entity name: " + name);

            } catch (SQLException e) {
                e.printStackTrace();
                throw new DaoException("Error process getUserMetaDataByName entity method: " + e.getMessage(), e);
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

    private User processResultSetMappingForUser(UserMetaData userMetaData) throws DaoException {

        Credentials credentials = credentialsJdbcDao.getById(userMetaData.getCredentialsId());
        List<Role> roleList = rolesMapJdbcDao.getByUserId(userMetaData.getId());
        PersonalInformation personalInformation = personalInformationJdbcDao.getById(userMetaData.getPersonalInformationId());

        return processResultSetMappingForUser(userMetaData, credentials, roleList, personalInformation);
    }

    private User processResultSetMappingForUser(UserMetaData userMetaData, Credentials credentials, List<Role> roleList,
                                                PersonalInformation personalInformation) {
        int id;
        boolean profileEnable;

        id = userMetaData.getId();
        profileEnable = userMetaData.isAccountNotLocked();

        return User.builder()
                .id(id)
                .credentials(credentials)
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
