package by.itacademy;

import by.itacademy.entities.user.User;
import by.itacademy.persistance.TaskDao;

import by.itacademy.exception.dao.DaoException;
import by.itacademy.persistance.UserDao;
import by.itacademy.persistance.jpa.dao.impl.TaskJpaDao;
import by.itacademy.persistance.jpa.dao.impl.UserJpaDao;
import by.itacademy.exception.security.authentication.UsernameNotFoundException;
import by.itacademy.entities.task.Task;

import org.h2.tools.Server;

import java.sql.SQLException;
import java.util.List;


public class Runner {

    // for connect with UI tool to database use url - jdbc:h2:tcp://localhost/mem:jdbc

    private static Server SERVER;

    static {
        System.setProperty("log4j.configurationFile", "logger/log4j2.xml");
        try {
            SERVER = Server.createTcpServer().start();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws DaoException, UsernameNotFoundException {

        TaskDao taskDao = TaskJpaDao.getInstance();


        UserDao userDao = UserJpaDao.getInstance();

        User user = (User) userDao.getByName("user1");
        System.out.println(user.toString());

        user = userDao.getById(4);
        System.out.println(user.toString());

        user.getCredential().setPassword("QWERTY");
        user = userDao.update(user);
        System.out.println(user.toString());

        userDao.delete(2);

        List<Task> taskList = taskDao.getByUserId(1);
        System.out.println(taskList.toString());

        Task task = taskDao.getById(1);
        System.out.println(task);
        SERVER.stop();
    }
}
