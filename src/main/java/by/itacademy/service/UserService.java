package by.itacademy.service;

import by.itacademy.exception.ApplicationBasedException;
import by.itacademy.exception.dao.DaoException;
import by.itacademy.entities.user.PersonalInformation;
import by.itacademy.entities.user.User;
import by.itacademy.persistance.UserDao;
import by.itacademy.persistance.jpa.dao.impl.UserJpaDao;
import lombok.extern.log4j.Log4j2;

import java.util.Arrays;
import java.util.List;

@Log4j2

public class UserService {

    private static UserService instance;

    private UserDao userDao;

    {
        userDao = UserJpaDao.getInstance();
    }

    public User getById(int id) throws ApplicationBasedException {
        return userDao.getById(id);
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

    public void deleteUser(User user) throws ApplicationBasedException {
        userDao.delete(user.getId());
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }
}
