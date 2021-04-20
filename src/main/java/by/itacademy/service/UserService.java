package by.itacademy.service;

import by.itacademy.exception.ApplicationBasedException;
import by.itacademy.entities.user.PersonalInformation;
import by.itacademy.entities.user.User;
import by.itacademy.exception.security.authentication.UsernameNotFoundException;
import by.itacademy.persistence.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return userDao.getById(id);
    }

    public User registerUser(User user) throws ApplicationBasedException {
        try {
            if (userDao.getByName(user.getLogin()) == null) {
                user = userDao.save(user);
            } else {
                throw new ApplicationBasedException("user already exist");
            }
        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
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

        userDao.save(user);
    }

    public void deleteUser(User user) throws ApplicationBasedException {
        int userId = user.getId();
        taskService.deleteByUserId(userId);
        unlockRequestMessageService.deleteByUserId(userId);
        roleDao.deleteByUserId(userId);
        userDao.delete(userId);
        personalInformationDao.deleteById(user.getPersonalInformation().getId());
        credentialsDao.deleteById(user.getCredential().getId());
    }
}
