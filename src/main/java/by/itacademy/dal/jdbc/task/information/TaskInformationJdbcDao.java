package by.itacademy.dal.jdbc.task.information;

import by.itacademy.dal.CrudDao;
import by.itacademy.model.task.TaskInformation;

import java.util.List;

public class TaskInformationJdbcDao implements CrudDao<TaskInformation> {

    private final String getByIdSql;
    private final String getAllSql;
    private final String updateSql;
    private final String createSql;
    private final String deleteSql;

    {
        getByIdSql = "SELECT 'id', 'description', 'filepath'  FROM task_information, WHERE id = ?;";
        getAllSql = "SELECT 'id', 'description', 'filepath'  FROM task_information;";
        updateSql = "UPDATE  task_information SET 'description' = ?, 'filepath' = ?, WHERE id = ?;";
        createSql = "INSERT INTO task_information('description', 'filepath') VALUES(?,?);";
        deleteSql = "DELETE FROM task_information, WHERE id = ?;";
    }

    @Override
    public TaskInformation getById(long id) {
        return null;
    }

    @Override
    public List<TaskInformation> getAll() {
        return null;
    }

    @Override
    public TaskInformation create(TaskInformation taskInformation) {
        return null;
    }

    @Override
    public TaskInformation update(TaskInformation taskInformation) {
        return null;
    }

    @Override
    public void delete(long id) {

    }
}
