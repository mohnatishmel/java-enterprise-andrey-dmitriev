package by.itacademy.dal;

import by.itacademy.exception.DaoException;

import java.util.List;

public interface CrudDao<T> {

    T getById(int id) throws DaoException;

    T create(T t) throws DaoException;

    T update(T t) throws DaoException;

    void delete(int id) throws DaoException;

}
