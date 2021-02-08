package by.itacademy.dal;

import by.itacademy.exception.DaoException;
import by.itacademy.model.task.Task;

import java.sql.Connection;
import java.util.List;

public interface TaskDao extends CrudDao<Task> {

    List<Task> getByUserId(int id) throws DaoException;

    void deleteByUserId(int userId, Connection connection) throws DaoException;

}
