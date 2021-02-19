package by.itacademy.dal.jdbc.mapper;

import by.itacademy.exception.dao.DaoException;
import by.itacademy.model.Unique;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractResultSetMapper<T extends Unique> implements ResultSetMapper<T> {

    abstract public T processResultSet(ResultSet rs) throws DaoException;

    @Override
    public T processResultSet(ResultSet rs, T t) throws DaoException {
        try {
            t.setId(rs.getInt(1));

        } catch (SQLException e) {
            throw new DaoException("Error mapping resultSet to an Object", e);
        }
        return t;
    }
}
