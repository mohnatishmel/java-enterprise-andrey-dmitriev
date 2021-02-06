package by.itacademy.dal;

import by.itacademy.exception.DaoException;
import by.itacademy.model.task.Task;

import java.util.List;

public interface TaskDao {

    List<Task> getByUserId(int id) throws DaoException;

    Task getById(int id) throws DaoException;

    Task create(Task task) throws DaoException;

    Task update(Task task) throws DaoException;

    void delete(int id) throws DaoException;
}
