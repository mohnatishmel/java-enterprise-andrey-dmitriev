package by.itacademy.service;

import by.itacademy.exception.ApplicationBasedException;
import by.itacademy.exception.dao.DaoException;
import by.itacademy.model.file.File;
import by.itacademy.model.message.UnlockRequestMessage;
import by.itacademy.model.task.Task;
import by.itacademy.model.user.PersonalInformation;
import by.itacademy.model.user.User;
import by.itacademy.persistance.TaskDao;
import by.itacademy.persistance.UserDao;
import by.itacademy.persistance.jdbc.dao.file.TaskFileJdbcDao;
import by.itacademy.persistance.jdbc.dao.message.UnlockRequestMessageJdbcDao;
import by.itacademy.persistance.jdbc.dao.task.TaskJdbcDao;
import by.itacademy.persistance.jdbc.dao.user.UserJdbcDao;
import by.itacademy.security.service.SecurityContext;
import lombok.extern.log4j.Log4j2;

import java.util.*;

@Log4j2

public class Service {
    private static Service instance;

    private UserDao userDao;
    private TaskDao taskDao;
    private TaskFileJdbcDao taskFleJdbcDao;
    private UnlockRequestMessageJdbcDao unlockRequestMessageJdbcDao;

    {
        userDao = UserJdbcDao.getInstance();
        taskDao = TaskJdbcDao.getInstance();
        taskFleJdbcDao = TaskFileJdbcDao.getInstance();
        unlockRequestMessageJdbcDao = UnlockRequestMessageJdbcDao.getInstance();
    }

    public List<Task> getTasksForUser(int id) throws ApplicationBasedException {
        List<Task> taskList = null;
            taskList = taskDao.getByUserId(id);
        return taskList;
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
            long i = task.getDeadLine().getTime();
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
        int userId = ((User)SecurityContext.getInstance().getPrincipal()).getId();
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

    public void uploadFileForTask(File file) throws ApplicationBasedException {
        try {
            taskFleJdbcDao.create(file);
        } catch (DaoException e) {
            log.debug(Arrays.toString(e.getStackTrace()));
            try {
                taskFleJdbcDao.update(file);
            } catch (DaoException daoException) {
                log.debug(Arrays.toString(e.getStackTrace()));
                throw new ApplicationBasedException(e);
            }
        }
    }

    public File getFile(long id) throws ApplicationBasedException {
        try {
            return taskFleJdbcDao.getById((int) id);
        } catch (DaoException e) {
            log.debug(Arrays.toString(e.getStackTrace()));
            throw new ApplicationBasedException(e);
        }
    }

    public User registerUser(User user) throws ApplicationBasedException {
        try {
            user = userDao.create(user);
        } catch (DaoException e) {
            log.debug(Arrays.toString(e.getStackTrace()));
            throw new ApplicationBasedException(e);
        }
        return user;
    }

    public List<User> getAllUsers() throws ApplicationBasedException{
        return userDao.getAll();
    }

    public void updateUser(User updateUser) throws ApplicationBasedException {
        User user = userDao.getById(updateUser.getId());

        PersonalInformation pi = updateUser.getPersonalInformation();
        boolean accountNotLocked = updateUser.isAccountNotLocked();

        if (pi != null) {
            user.setPersonalInformation(pi);
        }
         user.setAccountNotLocked(accountNotLocked);

        userDao.update(user);
    }

    public List<UnlockRequestMessage> getUnlockRequestMessages() throws ApplicationBasedException {
        return unlockRequestMessageJdbcDao.getAll();
    }

    public void createUnlockUserRequest(UnlockRequestMessage request) throws ApplicationBasedException {
        unlockRequestMessageJdbcDao.create(request);
    }

    public void deleteUnlockUserRequest(UnlockRequestMessage request) throws ApplicationBasedException {
        unlockRequestMessageJdbcDao.delete(request.getId());
    }

    public void resolveUnlockUserRequest(UnlockRequestMessage request) throws ApplicationBasedException {
        User user = userDao.getById(request.getUserId());
        user.setAccountNotLocked(true);
        userDao.update(user);

        unlockRequestMessageJdbcDao.delete(request.getId());
    }

    public void deleteUser(User user) throws ApplicationBasedException {
        userDao.delete(user.getId());
    }

    public static Service getInstance() {
        if (instance == null) {
            instance = new Service();
        }
        return instance;
    }
}
