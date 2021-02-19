package by.itacademy.dal.jdbc.mapper.user;

import by.itacademy.exception.dao.DaoException;
import by.itacademy.model.user.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleResultSetMapper {

    public Role processResultSetMapping(ResultSet rs) throws DaoException {
        try {
            int id = rs.getInt("role_id");
            String role = rs.getString("role");

            return new Role(id, role);
        } catch (SQLException e) {
            throw new DaoException("Error mapping resultSet to Role Object");
        }
    }
}
