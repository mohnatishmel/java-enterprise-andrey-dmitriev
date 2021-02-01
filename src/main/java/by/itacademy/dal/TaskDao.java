package by.itacademy.dal;

import by.itacademy.model.task.Task;

import java.util.List;

public interface TaskDao extends CrudDao<Task>{

    List<Task> findByUserId(long id);
}
