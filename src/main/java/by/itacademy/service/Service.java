package by.itacademy.service;

import by.itacademy.exception.ApplicationBasedException;
import by.itacademy.exception.dao.DaoException;
import by.itacademy.model.task.Task;
import by.itacademy.model.user.User;
import by.itacademy.persistance.TaskDao;
import by.itacademy.persistance.jdbc.dao.task.TaskJdbcDao;
import by.itacademy.security.service.SecurityContext;
import lombok.extern.log4j.Log4j2;

import java.util.Arrays;
import java.util.List;

@Log4j2

public class Service {
    private static Service instance;

    private TaskDao taskDao;

    {
        taskDao = TaskJdbcDao.getInstance();
    }

    public List<Task> getTasksForUser(int id) {
        List<Task> taskList = null;
        try {
            taskList = taskDao.getByUserId(id);

        } catch (DaoException e) {
            log.debug(Arrays.toString(e.getStackTrace()));
        }
        return taskList;
    }

    public void createTask(Task task) throws ApplicationBasedException {
        int userId = ((User)SecurityContext.getInstance().getPrincipal()).getId();
        task.setUserId(userId);
        try {
            taskDao.create(task);
        } catch (DaoException e) {
            log.debug(Arrays.toString(e.getStackTrace()));
            throw new ApplicationBasedException(e);
        }
    }

    public void updateTask(Task task) throws ApplicationBasedException {
        int userId = ((User)SecurityContext.getInstance().getPrincipal()).getId();
        task.setUserId(userId);
        try {
            taskDao.update(task);
        } catch (DaoException e) {
            log.debug(Arrays.toString(e.getStackTrace()));
            throw new ApplicationBasedException(e);
        }
    }

    public static Service getInstance() {
        if (instance == null) {
            instance = new Service();
        }
        return instance;
    }
}
