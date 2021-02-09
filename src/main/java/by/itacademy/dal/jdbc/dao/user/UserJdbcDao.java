package by.itacademy.dal.jdbc.dao.user;

import by.itacademy.dal.TaskDao;
import by.itacademy.dal.UserDao;
import by.itacademy.dal.jdbc.AbstractCrudJdbcDao;
import by.itacademy.dal.jdbc.connector.Connector;
import by.itacademy.dal.jdbc.query.user.UserJdbcSqlQueryHolder;
import by.itacademy.exception.DaoException;
import by.itacademy.dal.jdbc.dao.user.metadata.UserMetaData;
import by.itacademy.exception.UsernameNotFoundException;
import by.itacademy.model.user.Credential;
import by.itacademy.model.user.PersonalInformation;
import by.itacademy.model.user.Role;
import by.itacademy.model.user.User;
import by.itacademy.security.UserDetails;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDao extends AbstractCrudJdbcDao<User> implements UserDao {

    private final CredentialsJdbcDao credentialsJdbcDao;
    private final RolesMapJdbcDao rolesMapJdbcDao;
    private final PersonalInformationJdbcDao personalInformationJdbcDao;
    private final TaskDao taskDao;

    public UserJdbcDao(Connector connector, CredentialsJdbcDao credentialsJdbcDao, RolesMapJdbcDao rolesMapJdbcDao, PersonalInformationJdbcDao personalInformationJdbcDao, TaskDao taskDao) {
        super(connector);
        this.credentialsJdbcDao = credentialsJdbcDao;
        this.rolesMapJdbcDao = rolesMapJdbcDao;
        this.personalInformationJdbcDao = personalInformationJdbcDao;
        this.taskDao = taskDao;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        UserDetails userDetails;
        try (Connection connection = getConnector().getConnection()) {
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

    @Override
    public List<User> getAll() throws DaoException {
        List<User> userList;
        try (Connection connection = getConnector().getConnection()) {
            try {
                userList = getAllUsers(connection);
                connection.commit();

            } catch (DaoException e) {
                connection.rollback();
                throw new DaoException(e);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Error receive database connection: " + e.getMessage(), e);
        }
        return userList;
    }

    protected User getEntityById(int id, Connection connection) throws DaoException {
        return processResultSetMappingForUser(getUserMetaDataById(id, connection), connection);
    }

    private UserMetaData getUserMetaDataById(int id, Connection connection) throws DaoException {
        try {
            PreparedStatement ps = connection.prepareStatement(getSqlHolder().getByIdSql());
            ps.setInt(1,id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return processResultSetMappingForUserMetaData(rs);
                }
                throw new DaoException("Invalid entity id: " + id);

            } catch (SQLException e) {
                throw new DaoException("Error process getById entity method: " + e.getMessage(), e);
            }
        } catch (SQLException e) {
            throw new DaoException("Error initialize prepared statement: " + e.getMessage(), e);
        }
    }

    protected User createEntity(User user, Connection connection) throws DaoException {
        try {

            PreparedStatement statement = connection.prepareStatement(getSqlHolder().createSql(), Statement.RETURN_GENERATED_KEYS);

            Credential credential = credentialsJdbcDao.create(user.getCredential(), connection);
            PersonalInformation personalInformation = personalInformationJdbcDao.create(user.getPersonalInformation(), connection);
            List<Role> roleList = rolesMapJdbcDao.grantAuthoritiesToUser(user.getId(), (List<Role>) user.getAuthorities(), connection);

            statement.setBoolean(1, user.isAccountNonLocked());
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

    protected User updateEntity(User user, Connection connection) throws DaoException {
        try {

            PreparedStatement statement = connection.prepareStatement(getSqlHolder().updateSql());
            statement.setBoolean(1, user.isAccountNotLocked());
            statement.setInt(2, user.getId());
            statement.execute();

            credentialsJdbcDao.update(user.getCredential(), connection);
            personalInformationJdbcDao.update(user.getPersonalInformation(), connection);

        } catch (SQLException e) {
            throw new DaoException("Error process update entity method: " + e.getMessage(), e);
        }
        return user;
    }


    protected void deleteEntity(int id, Connection connection) throws DaoException {
        try {

            PreparedStatement statement = connection.prepareStatement(getSqlHolder().deleteSql());

            UserMetaData oldUser = getUserMetaDataById(id, connection);

            taskDao.deleteByUserId(id, connection);
            rolesMapJdbcDao.delete(oldUser.getId(), connection);

            statement.setInt(1, id);
            statement.executeUpdate();

            credentialsJdbcDao.delete(oldUser.getCredentialsId(), connection);
            personalInformationJdbcDao.delete(oldUser.getPersonalInformationId(), connection);

        } catch (SQLException e) {
            throw new DaoException("Error process delete entity method: " + e.getMessage());
        }
    }

    private User getUserByName(String name, Connection connection) throws DaoException {
        Credential credential = credentialsJdbcDao.getByLogin(name, connection);
        UserMetaData userMetaData = getUserMetaDataByCredentialsId(credential.getId(), connection);
        return processResultSetMappingForUser(userMetaData, connection);
    }

    private List<User> getAllUsers(Connection connection) throws DaoException {
        try {
            List<User> userList = new ArrayList<>();
            PreparedStatement ps = connection.prepareStatement(getSqlHolder().getAllSql());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    UserMetaData userMetaData = processResultSetMappingForUserMetaData(rs);
                    userList.add(processResultSetMappingForUser(userMetaData, connection));
                }
                return userList;

            } catch (SQLException e) {
                throw new DaoException("Error process getAllUsers method: " + e.getMessage(), e);
            }
        } catch (SQLException e) {
            throw new DaoException("Error initialize prepared statement: " + e.getMessage(), e);
        }
    }

    private UserMetaData getUserMetaDataByCredentialsId(int id, Connection connection) throws DaoException {
        try {
            PreparedStatement ps = connection.prepareStatement(getSqlHolder().getGetByNameSql());
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
            throw new DaoException("Error initialize prepared statement: " + e.getMessage(), e);
        }
    }

    private UserJdbcSqlQueryHolder getSqlHolder() {
        return new UserJdbcSqlQueryHolder();
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
}
