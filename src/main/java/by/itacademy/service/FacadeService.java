package by.itacademy.service;

import by.itacademy.exception.ApplicationBasedException;
import by.itacademy.exception.dao.DaoException;
import by.itacademy.exception.security.authorization.AuthorizationException;
import by.itacademy.model.file.File;
import by.itacademy.model.message.UnlockRequestMessage;
import by.itacademy.model.task.Task;
import by.itacademy.model.user.PersonalInformation;
import by.itacademy.model.user.Role;
import by.itacademy.model.user.User;
import by.itacademy.persistance.TaskDao;
import by.itacademy.persistance.UserDao;
import by.itacademy.persistance.jdbc.dao.file.TaskFileJdbcDao;
import by.itacademy.persistance.jdbc.dao.message.UnlockRequestMessageJdbcDao;
import by.itacademy.persistance.jdbc.dao.task.TaskJdbcDao;
import by.itacademy.persistance.jdbc.dao.user.UserJdbcDao;
import by.itacademy.security.service.SecurityService;
import lombok.extern.log4j.Log4j2;

import java.util.*;

@Log4j2

public class FacadeService {
    private static FacadeService instance;

    private SecurityService securityService;
    private FileService fileService;
    private UserService userService;

    private TaskDao taskDao;
    private TaskFileJdbcDao taskFleJdbcDao;
    private UnlockRequestMessageJdbcDao unlockRequestMessageJdbcDao;

    private TaskService taskService;

    {
        securityService = SecurityService.getInstance();
        fileService = FileService.getInstance();
        userService = UserService.getInstance();

        taskDao = TaskJdbcDao.getInstance();
        taskFleJdbcDao = TaskFileJdbcDao.getInstance();
        unlockRequestMessageJdbcDao = UnlockRequestMessageJdbcDao.getInstance();
        taskService = new TaskService(taskDao);
    }

    public List<Task> getTasksForUser(int id) throws ApplicationBasedException, AuthorizationException{
        if (securityService.authorizeForCurrentUser(id)
                & securityService.authorize(new Role("USER_ROLE"))) {
            return taskService.getTasksForUser(id);
        } else {
            throw new AuthorizationException("You are not authorized for this action");
        }
    }

    public List<Task> getTodayTasksForUser(int id) throws ApplicationBasedException, AuthorizationException {
        if (securityService.authorizeForCurrentUser(id)
                & securityService.authorize(new Role("USER_ROLE"))) {
            return taskService.getTodayTasksForUser(id);
        } else {
            throw new AuthorizationException("You are not authorized for this action");
        }
    }

    public List<Task> getTomorrowTasksForUser(int id) throws ApplicationBasedException, AuthorizationException {
        if (securityService.authorizeForCurrentUser(id)
                & securityService.authorize(new Role("USER_ROLE"))) {
            return taskService.getTomorrowTasksForUser(id);
        } else {
            throw new AuthorizationException("You are not authorized for this action");
        }
    }

    public List<Task> getSomedayTasksForUser(int id) throws ApplicationBasedException, AuthorizationException {
        if (securityService.authorizeForCurrentUser(id)
                & securityService.authorize(new Role("USER_ROLE"))) {
            return taskService.getSomedayTasksForUser(id);
        } else {
            throw new AuthorizationException("You are not authorized for this action");
        }
    }

    public List<Task> getTrashBoxTasksForUser(int id) throws ApplicationBasedException, AuthorizationException {
        if (securityService.authorizeForCurrentUser(id)
                & securityService.authorize(new Role("USER_ROLE"))) {
            return taskService.getTrashBoxTasksForUser(id);
        } else {
            throw new AuthorizationException("You are not authorized for this action");
        }
    }

    public List<Task> getFixedTasksForUser(int id) throws ApplicationBasedException, AuthorizationException {
        if (securityService.authorizeForCurrentUser(id)
                & securityService.authorize(new Role("USER_ROLE"))) {
            return taskService.getFixedTasksForUser(id);
        } else {
            throw new AuthorizationException("You are not authorized for this action");
        }
    }

    public void createTask(Task task) throws ApplicationBasedException, AuthorizationException {
        if (securityService.authorize(new Role("USER_ROLE"))) {
            taskService.createTask(task);
        } else {
            throw new AuthorizationException("You are not authorized for this action");
        }
    }

    public void updateTask(Task task) throws ApplicationBasedException, AuthorizationException {
        int userId = taskDao.getById(task.getId()).getUserId();
        if (securityService.authorizeForCurrentUser(userId)
                & securityService.authorize(new Role("USER_ROLE"))) {
            taskService.updateTask(task);
        } else {
            throw new AuthorizationException("You are not authorized for this action");
        }
    }

    public void deleteTask(int id) throws ApplicationBasedException, AuthorizationException {
        int userId = taskDao.getById(id).getUserId();
        if (securityService.authorizeForCurrentUser(userId)
                & securityService.authorize(new Role("USER_ROLE"))) {
            taskService.deleteTask(id);
        } else {
            throw new AuthorizationException("You are not authorized for this action");
        }
    }

    public void uploadFileForTask(File file) throws ApplicationBasedException, AuthorizationException {
        int userId = taskDao.getById((int) file.getId()).getUserId();
        if (securityService.authorizeForCurrentUser(userId)
                & securityService.authorize(new Role("USER_ROLE"))) {
            fileService.uploadFileForTask(file);
        } else {
            throw new AuthorizationException("You are not authorized for this action");
        }
    }

    public File getFile(long id) throws ApplicationBasedException, AuthorizationException {
        int userId = taskDao.getById((int) id).getUserId();
        if (securityService.authorizeForCurrentUser(userId)
                & securityService.authorize(new Role("USER_ROLE"))) {
            return fileService.getFile(id);
        } else {
            throw new AuthorizationException("You are not authorized for this action");
        }
    }

    public User registerUser(User user) throws ApplicationBasedException, AuthorizationException {
        return userService.registerUser(user);
    }

    public List<User> getAllUsers() throws ApplicationBasedException, AuthorizationException{
        if (securityService.authorize(new Role("ADMIN_ROLE"))) {
            return userService.getAllUsers();
        } else {
            throw new AuthorizationException("You are not authorized for this action");
        }
    }

    public void updateUser(User updateUser) throws ApplicationBasedException, AuthorizationException {
        userService.updateUser(updateUser);
    }

    public void updateUserIsLocked(User updateUser) throws ApplicationBasedException, AuthorizationException {
        if (securityService.authorize(new Role("ADMIN_ROLE"))) {
            userService.updateUser(updateUser);
        } else {
            throw new AuthorizationException("You are not authorized for this action");
        }
    }

    public void updateUserPersonalInformation(User updateUser) throws ApplicationBasedException, AuthorizationException {
        if (securityService.authorizeForCurrentUser(updateUser.getId())
                & securityService.authorize(new Role("USER_ROLE"))) {
            userService.updateUser(updateUser);
        } else {
            throw new AuthorizationException("You are not authorized for this action");
        }
    }

    public void deleteUser(User user) throws ApplicationBasedException, AuthorizationException {
        if (securityService.authorize(new Role("ADMIN_ROLE"))) {
            userService.deleteUser(user);
        } else {
            throw new AuthorizationException("You are not authorized for this action");
        }
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

    public void resolveUnlockUserRequest(UnlockRequestMessage request) throws ApplicationBasedException, AuthorizationException {
        if (securityService.authorize(new Role("ADMIN_ROLE"))) {
            User user = userService.getById(request.getUserId());
            user.setAccountNotLocked(true);
            updateUserIsLocked(user);

            unlockRequestMessageJdbcDao.delete(request.getId());
        } else {
            throw new AuthorizationException("You are not authorized for this action");
        }
    }

    public static FacadeService getInstance() {
        if (instance == null) {
            instance = new FacadeService();
        }
        return instance;
    }
}
