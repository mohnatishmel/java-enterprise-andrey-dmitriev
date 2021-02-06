package by.itacademy.dal.jdbc.user;

import by.itacademy.exception.DaoException;
import by.itacademy.model.user.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleJdbcDao {

    private static RoleJdbcDao instance = null;
    
    private static final String GET_BY_ID_SQL = "SELECT role_id, role  FROM roles WHERE role_id = ?;";
    private static final String GET_BY_ROLE_NAME_SQL = "SELECT role_id, role  FROM roles WHERE role = ?;";

    public Role getById(int id, Connection connection) throws DaoException {
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
    public Role getByRoleName(String role, Connection connection) throws DaoException {
        try {
            PreparedStatement ps = connection.prepareStatement(GET_BY_ROLE_NAME_SQL);
            ps.setString(1, role);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return processResultSetMapping(rs);
                }
                throw new DaoException("Invalid entity id: " + role);

            } catch (SQLException e) {
                e.printStackTrace();
                throw new DaoException("Error process getById entity method: " + e.getMessage(), e);
            }
        } catch (SQLException e) {
            throw new DaoException("Error receive database connection: " + e.getMessage(), e);
        }
    }

    public static RoleJdbcDao getInstance() {
        if (instance == null) {
            instance = new RoleJdbcDao();
        }
        return instance;
    }
    
    private Role processResultSetMapping(ResultSet rs) throws DaoException {
        try {
            int id = rs.getInt("role_id");
            String role = rs.getString("role");

            return new Role(id, role);
        } catch (SQLException e) {
            throw new DaoException("Error mapping resultSet to Role Object");
        }
    }
}
