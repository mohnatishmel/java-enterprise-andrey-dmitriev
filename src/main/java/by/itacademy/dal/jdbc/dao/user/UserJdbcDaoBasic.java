package by.itacademy.dal.jdbc.dao.user;

import by.itacademy.dal.TaskDao;
import by.itacademy.dal.UserDao;
import by.itacademy.dal.jdbc.AbstractBasicCrudJdbcDao;
import by.itacademy.dal.jdbc.connector.Connector;
import by.itacademy.dal.jdbc.query.user.UserJdbcSqlQueryHolder;
import by.itacademy.exception.DaoException;
import by.itacademy.dal.jdbc.dao.user.metadata.UserMetaData;
import by.itacademy.security.exception.web.authentication.UsernameNotFoundException;
import by.itacademy.model.user.Credential;
import by.itacademy.model.user.PersonalInformation;
import by.itacademy.model.user.Role;
import by.itacademy.model.user.User;
import by.itacademy.security.model.UserDetails;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Log4j2

public class UserJdbcDaoBasic extends AbstractBasicCrudJdbcDao<User> implements UserDao {

    private final CredentialsJdbcDao credentialsJdbcDao;
    private final RolesMapJdbcDao rolesMapJdbcDao;
    private final PersonalInformationJdbcDao personalInformationJdbcDao;
    private final TaskDao taskDao;

    public UserJdbcDaoBasic(Connector connector, CredentialsJdbcDao credentialsJdbcDao, RolesMapJdbcDao rolesMapJdbcDao, PersonalInformationJdbcDao personalInformationJdbcDao, TaskDao taskDao) {
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
                throw new UsernameNotFoundException(e);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            String message = "Error process getById entity method: ";
            log.debug(message, Arrays.toString(e.getStackTrace()));
            throw new UsernameNotFoundException(message + e.getMessage(), e);
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
            String message = "Error process getById entity method: ";
            log.debug(message, Arrays.toString(e.getStackTrace()));
            throw new DaoException(message + e.getMessage(), e);
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

            Credential credential = credentialsJdbcDao.create(user.getCredential());
            PersonalInformation personalInformation = personalInformationJdbcDao.create(user.getPersonalInformation());
            List<Role> roleList = rolesMapJdbcDao.grantAuthoritiesToUser(user.getId(), (List<Role>) user.getAuthorities());

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

            credentialsJdbcDao.update(user.getCredential());
            personalInformationJdbcDao.update(user.getPersonalInformation());

        } catch (SQLException e) {
            throw new DaoException("Error process update entity method: " + e.getMessage(), e);
        }
        return user;
    }


    protected void deleteEntity(int id, Connection connection) throws DaoException {
        try {

            PreparedStatement statement = connection.prepareStatement(getSqlHolder().deleteSql());

            UserMetaData oldUser = getUserMetaDataById(id,connection);

            taskDao.deleteByUserId(id);
            rolesMapJdbcDao.delete(oldUser.getId());

            statement.setInt(1, id);
            statement.executeUpdate();

            credentialsJdbcDao.delete(oldUser.getCredentialsId());
            personalInformationJdbcDao.delete(oldUser.getPersonalInformationId());

        } catch (SQLException e) {
            throw new DaoException("Error process delete entity method: " + e.getMessage());
        }
    }

    private User getUserByName(String name, Connection connection) throws DaoException {
        Credential credential = credentialsJdbcDao.getByLogin(name);
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

        Credential credential = credentialsJdbcDao.getById(userMetaData.getCredentialsId());
        List<Role> roleList = rolesMapJdbcDao.getByUserId(userMetaData.getId());
        PersonalInformation personalInformation = personalInformationJdbcDao.getById(userMetaData.getPersonalInformationId());

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
