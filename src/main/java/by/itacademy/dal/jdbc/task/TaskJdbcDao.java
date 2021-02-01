package by.itacademy.dal.jdbc.task;

import by.itacademy.dal.TaskDao;
import by.itacademy.model.task.Task;

import java.util.List;

public class TaskJdbcDao implements TaskDao {

    private final String findByUserId;
    private final String getByIdSql;
    private final String getAllSql;
    private final String updateSql;
    private final String createSql;
    private final String deleteSql;

    {
        findByUserId = "SELECT 'id', 'user_id', 'task_information_id', 'dead_line', 'fixed', 'in_basket'  FROM tasks, WHERE user_id = ?;";
        getByIdSql = "SELECT 'id', 'user_id', 'task_information_id', 'dead_line', 'fixed', 'in_basket'  FROM tasks, WHERE id = ?;";
        getAllSql = "SELECT 'id' 'user_id', 'task_information_id', 'dead_line', 'fixed', 'in_basket'  FROM tasks;";
        updateSql = "UPDATE  tasks SET 'user_id' = ?, 'task_information_id' = ?, 'dead_line' = ?, 'fixed' = ?, 'in_basket' = ?, WHERE id = ?;";
        createSql = "INSERT INTO tasks('user_id', 'task_information_id', 'dead_line', 'fixed', 'in_basket') VALUES(?,?,?,?,?);";
        deleteSql = "DELETE FROM tasks, WHERE id = ?;";
    }


    @Override
    public List<Task> findByUserId(long id) {
        return null;
    }

    @Override
    public Task getById(long id) {
        return null;
    }

    @Override
    public List<Task> getAll() {
        return null;
    }

    @Override
    public Task create(Task task) {
        return null;
    }

    @Override
    public Task update(Task task) {
        return null;
    }

    @Override
    public void delete(long id) {

    }
}
