package by.itacademy.persistence;

import by.itacademy.exception.dao.DaoException;
import by.itacademy.entities.task.Task;

import java.util.List;

public interface TaskDao extends CrudDao<Task> {

    List<Task> getByUserId(int id) throws DaoException;

    void deleteByUserId(int userId) throws DaoException;

}
