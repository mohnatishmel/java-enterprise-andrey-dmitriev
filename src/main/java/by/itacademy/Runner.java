package by.itacademy;

import by.itacademy.config.ApplicationConfig;
import by.itacademy.entities.user.User;
import by.itacademy.persistence.TaskDao;

import by.itacademy.exception.dao.DaoException;
import by.itacademy.persistence.UserDao;
import by.itacademy.persistence.jpa.dao.impl.TaskJpaDao;
import by.itacademy.persistence.jpa.dao.impl.UserJpaDao;
import by.itacademy.exception.security.authentication.UsernameNotFoundException;
import by.itacademy.entities.task.Task;

import by.itacademy.service.FacadeService;
import org.h2.tools.Server;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;


public class Runner {

    // for connect with UI tool to database use url - jdbc:h2:tcp://localhost/mem:jdbc

    private static Server SERVER;

    public static final org.springframework.context.ApplicationContext APPLICATION_CONTEXT =
            new AnnotationConfigApplicationContext(ApplicationConfig.class);

    static {
        System.setProperty("log4j.configurationFile", "logger/log4j2.xml");
        try {
            SERVER = Server.createTcpServer().start();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws DaoException, UsernameNotFoundException {

        FacadeService facadeService = APPLICATION_CONTEXT.getBean(FacadeService.class);

        TaskDao taskDao = APPLICATION_CONTEXT.getBean(TaskDao.class);

        Task task = taskDao.getById(1);
        System.out.println(task);

        UserDao userDao = APPLICATION_CONTEXT.getBean(UserJpaDao.class);

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

        SERVER.stop();
    }
}
