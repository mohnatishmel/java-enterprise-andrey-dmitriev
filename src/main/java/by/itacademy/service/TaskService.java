package by.itacademy.service;

import by.itacademy.exception.ApplicationBasedException;
import by.itacademy.entities.task.Task;
import by.itacademy.entities.user.User;
import by.itacademy.persistence.TaskDao;
import by.itacademy.security.service.SecurityContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static by.itacademy.util.UtilDate.getDayStartAndDayEndInMilliseconds;


@Service
public class TaskService {

    private TaskDao taskDao;

    private SecurityContext securityContext;

    @Autowired
    public TaskService(TaskDao taskDao, SecurityContext securityContext) {
        this.taskDao = taskDao;
        this.securityContext = securityContext;
    }

    public List<Task> getTasksForUser(int id) throws ApplicationBasedException {
        try {
            return taskDao.getByUserId(id);
        } catch (
                DataAccessException e) {
            throw new ApplicationBasedException(e);
        }
    }

    public Page<Task> getTodayTasksForUser(int id, Pageable pageable) throws ApplicationBasedException {
        try {
            long[] timeRange = getDayStartAndDayEndInMilliseconds(0);
            return taskDao.getByDeadLineBetweenAndUserIdAndFixedIsFalseAndInBasketIsFalse
                    (new java.sql.Date(timeRange[0]), new java.sql.Date(timeRange[1]), id, pageable);
        } catch (DataAccessException e) {
            throw new ApplicationBasedException(e);
        }
    }

    public Page<Task> getTomorrowTasksForUser(int id, Pageable pageable) throws ApplicationBasedException {
        try {
            long[] timeRange = getDayStartAndDayEndInMilliseconds(1);
            return taskDao.getByDeadLineBetweenAndUserIdAndFixedIsFalseAndInBasketIsFalse
                    (new java.sql.Date(timeRange[0]), new java.sql.Date(timeRange[1]), id, pageable);
        } catch (DataAccessException e) {
            throw new ApplicationBasedException(e);
        }
    }

    public Page<Task> getSomedayTasksForUser(int id, Pageable pageable) throws ApplicationBasedException {
        try {
            long[] timeRange = getDayStartAndDayEndInMilliseconds(2);
            return taskDao.getByDeadLineIsGreaterThanAndUserIdAndFixedIsFalseAndInBasketIsFalse
                    (new java.sql.Date(timeRange[0]), id, pageable);
        } catch (DataAccessException e) {
            throw new ApplicationBasedException(e);
        }
    }

    public Page<Task> getTrashBoxTasksForUser(int id, Pageable pageable) throws ApplicationBasedException {
        try {
            return taskDao.getByInBasketIsTrueAndUserId(id, pageable);
        } catch (DataAccessException e) {
            throw new ApplicationBasedException(e);
        }
    }

    public Page<Task> getFixedTasksForUser(int id, Pageable pageable) throws ApplicationBasedException {
        try {
            return taskDao.getByFixedIsTrueAndUserId(id, pageable);
        } catch (DataAccessException e) {
            throw new ApplicationBasedException(e);
        }
    }

    public void createTask(Task task) throws ApplicationBasedException {
        try {
            int userId = ((User) securityContext.getPrincipal()).getId();
            task.setUserId(userId);
            taskDao.create(task.getUserId(),
                    task.getName(),
                    task.getDescription(),
                    new java.sql.Date(task.getDeadLine().getTime()),
                    task.isFixed(),
                    task.isInBasket());
        } catch (DataAccessException e) {
            throw new ApplicationBasedException(e);
        }
    }

    public void updateTask(Task task) throws ApplicationBasedException {
        try {
            int userId = ((User) securityContext.getPrincipal()).getId();
            task.setUserId(userId);
            taskDao.update(task.getId(),
                    task.getName(),
                    task.getDescription(),
                    new java.sql.Date(task.getDeadLine().getTime()),
                    task.isFixed(),
                    task.isInBasket());
        } catch (DataAccessException e) {
            throw new ApplicationBasedException(e);
        }
    }

    public void deleteTask(int id) throws ApplicationBasedException {
        try {
            taskDao.deleteById(id);
        } catch (DataAccessException e) {
            throw new ApplicationBasedException(e);
        }
    }

    public void deleteByUserId(int id) throws ApplicationBasedException {
        try {
            taskDao.deleteByUserId(id);
        } catch (DataAccessException e) {
            throw new ApplicationBasedException(e);
        }
    }
}
