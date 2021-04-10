package by.itacademy.service;

import by.itacademy.exception.ApplicationBasedException;
import by.itacademy.entities.task.Task;
import by.itacademy.entities.user.User;
import by.itacademy.persistance.TaskDao;
import by.itacademy.persistance.jpa.dao.impl.TaskJpaDao;
import by.itacademy.security.service.SecurityContext;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TaskService {

    private static TaskService instance;

    private TaskDao taskDao;

    private TaskService() {
    }

    {
        taskDao = TaskJpaDao.getInstance();
    }

    public List<Task> getTasksForUser(int id) throws ApplicationBasedException {
        return taskDao.getByUserId(id);
    }

    public List<Task> getTodayTasksForUser(int id) throws ApplicationBasedException {
        List<Task> taskList = getTasksForUser(id);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        long today = calendar.getTimeInMillis();
        System.out.println(new Date(today));

        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1);

        long tomorrow = calendar.getTimeInMillis();
        System.out.println(new Date(tomorrow));

        List<Task> todayTaskList = new ArrayList<>();
        for (Task task : taskList) {
            System.out.println(task.getDeadLine());
            System.out.println(tomorrow - task.getDeadLine().getTime());
            if (task.getDeadLine().getTime() < tomorrow
                    && !task.isInBasket()
                    && !task.isFixed()) {
                todayTaskList.add(task);
            }
        }
        return todayTaskList;
    }

    public List<Task> getTomorrowTasksForUser(int id) throws ApplicationBasedException {
        List<Task> taskList = getTasksForUser(id);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1);

        long tomorrow = calendar.getTimeInMillis();

        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1);

        long dayAfterTomorrow = calendar.getTimeInMillis();

        List<Task> tomorrowTaskList = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getDeadLine().getTime() >= tomorrow
                    && task.getDeadLine().getTime() < dayAfterTomorrow
                    && !task.isInBasket()
                    && !task.isFixed()) {
                tomorrowTaskList.add(task);
            }
        }

        return tomorrowTaskList;
    }

    public List<Task> getSomedayTasksForUser(int id) throws ApplicationBasedException {
        List<Task> taskList = getTasksForUser(id);


        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 2);

        long dayAfterTomorrow = calendar.getTimeInMillis();

        List<Task> somedayTaskList = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getDeadLine().getTime() >= dayAfterTomorrow
                    && !task.isInBasket()
                    && !task.isFixed()) {
                somedayTaskList.add(task);
            }
        }

        return somedayTaskList;
    }

    public List<Task> getTrashBoxTasksForUser(int id) throws ApplicationBasedException {
        List<Task> taskList = getTasksForUser(id);
        List<Task> trashBox = new ArrayList<>();

        for (Task task : taskList) {
            if (task.isInBasket()) {
                trashBox.add(task);
            }
        }
        return trashBox;
    }

    public List<Task> getFixedTasksForUser(int id) throws ApplicationBasedException {
        List<Task> taskList = getTasksForUser(id);
        List<Task> fixed = new ArrayList<>();

        for (Task task : taskList) {
            if (task.isFixed()) {
                fixed.add(task);
            }
        }
        return fixed;
    }

    public void createTask(Task task) throws ApplicationBasedException {
        int userId = ((User) SecurityContext.getInstance().getPrincipal()).getId();
        task.setUserId(userId);
        taskDao.create(task);
    }

    public void updateTask(Task task) throws ApplicationBasedException {
        int userId = ((User)SecurityContext.getInstance().getPrincipal()).getId();
        task.setUserId(userId);
        taskDao.update(task);
    }

    public void deleteTask(int id) throws ApplicationBasedException {
        taskDao.delete(id);
    }

    public static TaskService getInstance() {
        if (instance == null) {
            instance = new TaskService();
        }
        return instance;
    }
}
