package by.itacademy.service;

import by.itacademy.exception.ApplicationBasedException;
import by.itacademy.exception.dao.DaoException;
import by.itacademy.entities.user.PersonalInformation;
import by.itacademy.entities.user.User;
import by.itacademy.persistence.UserDao;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Log4j2

@Service
public class UserService {

    private UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
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
}
