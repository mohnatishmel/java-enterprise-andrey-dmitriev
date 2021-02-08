package by.itacademy.dal.jdbc.mapper;

import by.itacademy.exception.DaoException;

import java.sql.ResultSet;

public interface ResultSetMapper<T> {

    T processResultSet(ResultSet rs) throws DaoException;

    T processResultSet(ResultSet rs, T t) throws DaoException;
}
