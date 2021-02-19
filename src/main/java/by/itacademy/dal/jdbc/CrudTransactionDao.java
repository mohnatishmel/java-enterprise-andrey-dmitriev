package by.itacademy.dal.jdbc;

import by.itacademy.exception.dao.DaoException;

import java.sql.Connection;

public interface CrudTransactionDao<T> {

    T getById(int id, Connection connection) throws DaoException;

    T create(T t, Connection connection) throws DaoException;

    T update(T t, Connection connection) throws DaoException;

    void delete(int id, Connection connection) throws DaoException;

}
