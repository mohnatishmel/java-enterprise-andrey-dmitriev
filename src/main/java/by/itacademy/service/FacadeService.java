package by.itacademy.service;

import by.itacademy.exception.ApplicationBasedException;;
import by.itacademy.exception.security.authorization.AuthorizationException;
import by.itacademy.entities.file.File;
import by.itacademy.entities.message.UnlockRequestMessage;
import by.itacademy.entities.task.Task;
import by.itacademy.entities.user.Role;
import by.itacademy.entities.user.User;
import by.itacademy.persistence.TaskDao;
import by.itacademy.security.service.SecurityService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Log4j2
@Service
public class FacadeService {

    private SecurityService securityService;
    private FileService fileService;
    private UserService userService;
    private UnlockRequestMessageService unlockRequestMessageService;
    private TaskService taskService;
    private TaskDao taskDao;

    @Autowired
    public FacadeService(SecurityService securityService, FileService fileService,
                         UserService userService, UnlockRequestMessageService unlockRequestMessageService,
                         TaskService taskService, TaskDao taskDao) {
        this.securityService = securityService;
        this.fileService = fileService;
        this.userService = userService;
        this.unlockRequestMessageService = unlockRequestMessageService;
        this.taskService = taskService;
        this.taskDao = taskDao;
    }

    public List<Task> getTasksForUser(int id) throws ApplicationBasedException, AuthorizationException{
        if (securityService.authorizeForCurrentUser(id)
                & securityService.authorize(new Role("USER_ROLE"))) {
            return taskService.getTasksForUser(id);
        } else {
            throw new AuthorizationException("You are not authorized for this action");
        }
    }

    public Page<Task> getTodayTasksForUser(int id, Pageable pageable) throws ApplicationBasedException, AuthorizationException {
        if (securityService.authorizeForCurrentUser(id)
                & securityService.authorize(new Role("USER_ROLE"))) {
            return taskService.getTodayTasksForUser(id, pageable);
        } else {
            throw new AuthorizationException("You are not authorized for this action");
        }
    }

    public Page<Task> getTomorrowTasksForUser(int id, Pageable pageable) throws ApplicationBasedException, AuthorizationException {
        if (securityService.authorizeForCurrentUser(id)
                & securityService.authorize(new Role("USER_ROLE"))) {
            return taskService.getTomorrowTasksForUser(id, pageable);
        } else {
            throw new AuthorizationException("You are not authorized for this action");
        }
    }

    public Page<Task> getSomedayTasksForUser(int id, Pageable pageable) throws ApplicationBasedException, AuthorizationException {
        if (securityService.authorizeForCurrentUser(id)
                & securityService.authorize(new Role("USER_ROLE"))) {
            return taskService.getSomedayTasksForUser(id, pageable);
        } else {
            throw new AuthorizationException("You are not authorized for this action");
        }
    }

    public Page<Task> getTrashBoxTasksForUser(int id, Pageable pageable) throws ApplicationBasedException, AuthorizationException {
        if (securityService.authorizeForCurrentUser(id)
                & securityService.authorize(new Role("USER_ROLE"))) {
            return taskService.getTrashBoxTasksForUser(id, pageable);
        } else {
            throw new AuthorizationException("You are not authorized for this action");
        }
    }

    public Page<Task> getFixedTasksForUser(int id, Pageable pageable) throws ApplicationBasedException, AuthorizationException {
        if (securityService.authorizeForCurrentUser(id)
                & securityService.authorize(new Role("USER_ROLE"))) {
            return taskService.getFixedTasksForUser(id, pageable);
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
}
