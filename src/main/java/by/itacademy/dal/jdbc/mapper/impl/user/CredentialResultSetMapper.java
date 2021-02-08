package by.itacademy.dal.jdbc.mapper.impl.user;

import by.itacademy.dal.jdbc.mapper.AbstractResultSetMapper;
import by.itacademy.dal.jdbc.mapper.ResultSetMapper;
import by.itacademy.exception.DaoException;
import by.itacademy.model.user.Credential;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CredentialResultSetMapper extends AbstractResultSetMapper<Credential> {

    @Override
    public Credential processResultSet(ResultSet rs) throws DaoException {
        try {
            int id = rs.getInt("credentials_id");
            String login = rs.getString("login");
            String password = rs.getString("password");

            return new Credential(id, login, password);
        } catch (SQLException e) {
            throw new DaoException("Error mapping resultSet to Credentials Object");
        }
    }
}
