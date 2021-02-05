package by.itacademy.dal;

import by.itacademy.exception.DaoException;
import by.itacademy.model.task.Task;

import java.util.List;

public interface TaskDao extends CrudDao<Task>{

    List<Task> findByUserId(int id) throws DaoException;
}
