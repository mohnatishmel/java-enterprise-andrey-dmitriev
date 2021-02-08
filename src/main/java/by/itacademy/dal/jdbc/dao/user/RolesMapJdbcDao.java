package by.itacademy.dal.jdbc.dao.user;

import by.itacademy.exception.DaoException;
import by.itacademy.model.user.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RolesMapJdbcDao {

    private final RoleJdbcDao roleJdbcDao;

    private static RolesMapJdbcDao instance = null;


    private static final String GET_BY_USER_ID_SQL = "SELECT role_id FROM roles_map WHERE user_id = ?;";
    private static final String CREATE_SQL = "INSERT INTO roles_map(user_id, role_id) VALUES(?,?);";
    private static final String DELETE_SQL = "DELETE FROM roles_map WHERE user_id = ?;";

    {
        roleJdbcDao = RoleJdbcDao.getInstance();
    }

    public List<Role> getByUserId(int id, Connection connection) throws DaoException {
        try {
            PreparedStatement ps = connection.prepareStatement(GET_BY_USER_ID_SQL);
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                return processResultSetMapping(rs, connection);

            } catch (SQLException e) {
                e.printStackTrace();
                throw new DaoException("Error process getById entity method: " + e.getMessage(), e);
            }
        } catch (SQLException e) {
            throw new DaoException("Error receive database connection: " + e.getMessage(), e);
        }
    }

    public List<Role> grantAuthoritiesToUser(int userId, List<Role> roles, Connection connection) throws DaoException {
        for (Role role : roles) {
            try {
                role = roleJdbcDao.getByRoleName(role.getAuthority(), connection);
                grantAuthorityToUser(userId, role, connection);
            } catch (DaoException e) {
                throw new DaoException("Error during role mapping for user_id = "
                        + userId + ". Role_name '" + role + "' not exist", e);
            }
        }
        return roles;
    }

    public void grantAuthorityToUser(int userId, Role role, Connection connection) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(CREATE_SQL);
            statement.setInt(1, userId);
            statement.setInt(1, role.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Error process create entity: " + e.getMessage(), e);
        }
    }

    public void delete(int userId, Connection connection) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_SQL);
            statement.setInt(1, userId);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Error process delete entity method: " + e.getMessage());
        }
    }

    public static RolesMapJdbcDao getInstance() {
        if (instance == null) {
            instance = new RolesMapJdbcDao();
        }
        return instance;
    }

    private List<Role> processResultSetMapping(ResultSet rs, Connection connection) throws DaoException {
        List<Role> roleList = new ArrayList<>();
        try {
            while (rs.next()) {
                Role role = roleJdbcDao.getById(rs.getInt("role_id"), connection);
                roleList.add(role);
            }
            return roleList;

        } catch (SQLException e) {
            throw new DaoException("Error mapping resultSet to Role Object");
        }
    }
}
