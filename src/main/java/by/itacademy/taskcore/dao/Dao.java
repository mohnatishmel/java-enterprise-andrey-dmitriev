package by.itacademy.taskcore.dao;

import by.itacademy.taskcore.exception.DaoException;

import java.util.List;

public interface Dao {

    List<String> read(String name) throws DaoException;
}
