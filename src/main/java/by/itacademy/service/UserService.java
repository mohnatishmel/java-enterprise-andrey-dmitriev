package by.itacademy.service;

import by.itacademy.entities.user.Role;
import by.itacademy.exception.ApplicationBasedException;
import by.itacademy.entities.user.PersonalInformation;
import by.itacademy.entities.user.User;
import by.itacademy.exception.security.authorization.AuthorizationException;
import by.itacademy.persistence.*;
import by.itacademy.security.model.user.GrantedAuthority;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Log4j2

@Service
public class UserService {

    private UserDao userDao;
    private PersonalInformationDao personalInformationDao;
    private CredentialsDao credentialsDao;
    private RoleDao roleDao;
    private TaskService taskService;
    private UnlockRequestMessageService unlockRequestMessageService;

    @Autowired
    public UserService(UserDao userDao, PersonalInformationDao personalInformationDao,
                       CredentialsDao credentialsDao, RoleDao roleDao,
                       TaskService taskService, UnlockRequestMessageService unlockRequestMessageService) {
        this.userDao = userDao;
        this.personalInformationDao = personalInformationDao;
        this.credentialsDao = credentialsDao;
        this.roleDao = roleDao;
        this.taskService = taskService;
        this.unlockRequestMessageService = unlockRequestMessageService;
    }

    public User getById(int id) throws ApplicationBasedException {
        try {
            return userDao.getById(id);
        } catch (DataAccessException e) {
            throw new ApplicationBasedException(e);
        }
    }

    public List<User> getAllLocked(int excludeId) throws ApplicationBasedException {
        try {
            return userDao.getByAccountNonLocked(false, excludeId);
        } catch (DataAccessException e) {
            throw new ApplicationBasedException(e);
        }
    }

    public List<User> getAllNotLocked(int excludeId) throws ApplicationBasedException {
        try {
            return userDao.getByAccountNonLocked(true, excludeId);
        } catch (DataAccessException e) {
            throw new ApplicationBasedException(e);
        }
    }

    public User registerUser(User user) throws AuthorizationException {
        if (userDao.getByName(user.getLogin()) == null) {
            List<Role> roles = new ArrayList<>();
            for (GrantedAuthority role : user.getAuthorities()) {
                role =  roleDao.getRoleByRoleName(role.getAuthority());
                role.getAuthority();
                ((Role)role).addUser(user);
                roles.add((Role) role);
            }
            user.setRoles(roles);
            user = userDao.save(user);
        } else {
            throw new AuthorizationException("User name already exist");
        }
        return user;
    }

    public List<User> getAllUsers() throws ApplicationBasedException {
        try {
            return userDao.getAll();
        } catch (DataAccessException e) {
            throw new ApplicationBasedException(e);
        }
    }

    public void updateUser(User updateUser) throws ApplicationBasedException {
        try {
            User user = userDao.getById(updateUser.getId());

            PersonalInformation pi = updateUser.getPersonalInformation();
            boolean accountNotLocked = updateUser.isAccountNotLocked();

            if (pi != null) {
                user.setPersonalInformation(pi);
            }
            user.setAccountNotLocked(accountNotLocked);

            userDao.save(user);
        } catch (DataAccessException e) {
            throw new ApplicationBasedException(e);
        }
    }

    public void deleteUser(User user) throws ApplicationBasedException {
        try {
            int userId = user.getId();
            user = userDao.getById(userId);
            taskService.deleteByUserId(userId);
            unlockRequestMessageService.deleteByUserId(userId);
            roleDao.deleteByUserId(userId);
            userDao.delete(userId);
            personalInformationDao.deleteById(user.getPersonalInformation().getId());
            credentialsDao.deleteById(user.getCredential().getId());

        } catch (DataAccessException e) {
            throw new ApplicationBasedException(e);
        }
    }
}
