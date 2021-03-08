package by.itacademy.persistance.jdbc.dao.user;

import by.itacademy.persistance.jdbc.BaseAbstractJdbcDao;
import by.itacademy.persistance.jdbc.connector.Connector;
import by.itacademy.persistance.jdbc.query.user.RoleMapJdbcSqlQueryHolder;
import by.itacademy.exception.dao.DaoException;
import by.itacademy.model.user.Role;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Log4j2

public class RolesMapJdbcDao extends BaseAbstractJdbcDao {

    public RolesMapJdbcDao(Connector connector, RoleJdbcDao roleJdbcDao) {
        super(connector);
        this.roleJdbcDao = roleJdbcDao;
    }

    private final RoleJdbcDao roleJdbcDao;

    public List<Role> getByUserId(int id) throws DaoException {
        try (Connection connection = getConnector().getConnection()) {
            PreparedStatement ps = connection.prepareStatement(getSqlHolder().getByUserIdSql());
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return processResultSetMapping(rs);

            } catch (SQLException e) {
                e.printStackTrace();
                throw new DaoException("Error process getById entity method: " + e.getMessage(), e);
            }

        } catch (SQLException e) {
            String message = "Error process getById entity method: ";
            log.debug(message, Arrays.toString(e.getStackTrace()));
            throw new DaoException(message + e.getMessage(), e);
        }
    }

    public List<Role> grantAuthoritiesToUser(int userId, List<Role> roles) throws DaoException {
            for (Role role : roles) {
                try {
                    role = roleJdbcDao.getByRoleName(role.getAuthority());
                    grantAuthorityToUser(userId, role);
                } catch (DaoException e) {
                    throw new DaoException("Error during role mapping for user_id = "
                            + userId + ". Role_name '" + role + "' not exist", e);
                }
            }
            return roles;
    }

    public void grantAuthorityToUser(int userId, Role role) throws DaoException {
        try (Connection connection = getConnector().getConnection()) {
            try {
                PreparedStatement statement = connection.prepareStatement(getSqlHolder().createSql());
                statement.setInt(1, userId);
                statement.setInt(2, role.getId());
                statement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
                throw new DaoException("Error process create entity: " + e.getMessage(), e);
            }

        } catch (SQLException e) {
            String message = "Error process getById entity method: ";
            log.debug(message, Arrays.toString(e.getStackTrace()));
            throw new DaoException(message + e.getMessage(), e);
        }
    }

    public void delete(int userId) throws DaoException {
        try (Connection connection = getConnector().getConnection()) {
            try {
                PreparedStatement statement = connection.prepareStatement(getSqlHolder().deleteSql());
                statement.setInt(1, userId);
                statement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
                throw new DaoException("Error process delete entity method: " + e.getMessage());
            }

        } catch (SQLException e) {
            String message = "Error process getById entity method: ";
            log.debug(message, Arrays.toString(e.getStackTrace()));
            throw new DaoException(message + e.getMessage(), e);
        }
    }

    private RoleMapJdbcSqlQueryHolder getSqlHolder() {
        return new RoleMapJdbcSqlQueryHolder();
    }

    private List<Role> processResultSetMapping(ResultSet rs) throws DaoException {
        List<Role> roleList = new ArrayList<>();
        try (Connection connection = getConnector().getConnection()) {
            try {
                while (rs.next()) {
                    Role role = roleJdbcDao.getById(rs.getInt("role_id"));
                    roleList.add(role);
                }
                return roleList;

            } catch (SQLException e) {
                throw new DaoException("Error mapping resultSet to Role Object");
            }

        } catch (SQLException e) {
            String message = "Error process getById entity method: ";
            log.debug(message, Arrays.toString(e.getStackTrace()));
            throw new DaoException(message + e.getMessage(), e);
        }
    }
}
