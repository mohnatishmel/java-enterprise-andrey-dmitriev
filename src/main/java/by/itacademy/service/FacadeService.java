package by.itacademy.service;

import by.itacademy.exception.ApplicationBasedException;;
import by.itacademy.exception.security.authorization.AuthorizationException;
import by.itacademy.entities.file.File;
import by.itacademy.entities.message.UnlockRequestMessage;
import by.itacademy.entities.task.Task;
import by.itacademy.entities.user.Role;
import by.itacademy.entities.user.User;
import by.itacademy.persistance.TaskDao;
import by.itacademy.persistance.jpa.dao.impl.TaskJpaDao;
import by.itacademy.security.service.SecurityService;
import lombok.extern.log4j.Log4j2;

import java.util.*;

@Log4j2

public class FacadeService {
    private static FacadeService instance;

    private SecurityService securityService;
    private FileService fileService;
    private UserService userService;
    private UnlockRequestMessageService unlockRequestMessageService;
    private TaskService taskService;

    private TaskDao taskDao;

    {
        securityService = SecurityService.getInstance();
        fileService = FileService.getInstance();
        userService = UserService.getInstance();
        unlockRequestMessageService= UnlockRequestMessageService.getInstance();
        taskService = TaskService.getInstance();

        taskDao = TaskJpaDao.getInstance();
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

    public List<UnlockRequestMessage> getUnlockRequestMessages() throws ApplicationBasedException, AuthorizationException {
        if (securityService.authorize(new Role("ADMIN_ROLE"))) {
            return unlockRequestMessageService.getUnlockRequestMessages();
        } else {
            throw new AuthorizationException("You are not authorized for this action");
        }
    }

    public void createUnlockUserRequest(UnlockRequestMessage requestMessage) throws ApplicationBasedException, AuthorizationException {
        if (securityService.authorizeForCurrentUser(requestMessage.getUserId())
                & securityService.authorize(new Role("USER_ROLE"))) {
            unlockRequestMessageService.createUnlockUserRequest(requestMessage);
        } else {
            throw new AuthorizationException("You are not authorized for this action");
        }
    }

    public void deleteUnlockUserRequest(UnlockRequestMessage requestMessage) throws ApplicationBasedException, AuthorizationException {
        if (securityService.authorize(new Role("ADMIN_ROLE"))) {
            unlockRequestMessageService.deleteUnlockUserRequest(requestMessage);
        } else {
            throw new AuthorizationException("You are not authorized for this action");
        }
    }

    public void resolveUnlockUserRequest(UnlockRequestMessage requestMessage) throws ApplicationBasedException, AuthorizationException {
        if (securityService.authorize(new Role("ADMIN_ROLE"))) {
            User user = userService.getById(requestMessage.getUserId());
            user.setAccountNotLocked(true);
            updateUserIsLocked(user);

            unlockRequestMessageService.deleteUnlockUserRequest(requestMessage);
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
