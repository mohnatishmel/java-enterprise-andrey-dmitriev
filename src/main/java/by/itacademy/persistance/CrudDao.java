package by.itacademy.persistance;

import by.itacademy.exception.dao.DaoException;

public interface CrudDao<T> {

    T getById(int id) throws DaoException;

    T create(T t) throws DaoException;

    T update(T t) throws DaoException;

    void delete(int id) throws DaoException;

}
