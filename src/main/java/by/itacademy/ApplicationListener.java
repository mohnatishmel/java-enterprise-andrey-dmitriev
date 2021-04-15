package by.itacademy;


import by.itacademy.entities.task.Task;
import by.itacademy.entities.user.User;
import by.itacademy.persistance.TaskDao;
import by.itacademy.persistance.UserDao;
import by.itacademy.persistance.jpa.dao.impl.TaskJpaDao;
import by.itacademy.persistance.jpa.dao.impl.UserJpaDao;
import by.itacademy.security.service.SecurityContext;
import by.itacademy.service.FacadeService;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.h2.tools.Server;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import java.sql.SQLException;
import java.util.Date;

@Log4j2

@WebListener
public class ApplicationListener implements ServletContextListener {

    // for connect with UI tool to database use url - jdbc:h2:tcp://localhost/mem:jdbc

    private static Server SERVER;
//    private static Connector DATABASE_CONNECTOR;

    @SneakyThrows
    public void contextInitialized(ServletContextEvent event) {

        System.setProperty("log4j.configurationFile", "logger/log4j2.xml");

        try {
            SERVER = Server.createTcpServer().start();
            log.debug("H2 Server started");

        } catch (SQLException e) {
            e.printStackTrace();
            log.debug("Error starting H2 Server", e);
        }


        TaskDao taskDao = TaskJpaDao.getInstance();
//        UserDao userDao = UserJpaDao.getInstance();
//        FacadeService facadeService = FacadeService.getInstance();
//
//
//        User user = (User) userDao.getByName("user1");
//        System.out.println(user.toString());

//        user = userDao.getById(1);
//        System.out.println(user.toString());

//        user.getCredential().setPassword("QWERTY");
//        user = userDao.update(user);
//        System.out.println(user.toString());

//        userDao.delete(user.getId());

//        List<Task> taskList = taskDao.getByUserId(1);
//        System.out.println(taskList.toString());

//        SecurityContext.getInstance().setPrincipal(user);
//        Task task = Task.builder()
//                .userId(user.getId())
//                .name("qwe")
//                .description("asd")
//                .fixed(false)
//                .inBasket(false)
//                .user(user)
//                .deadLine(new Date())
//                .build();
//        user.addTask(task);
//       facadeService.createTask(task);
        //SERVER.stop();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
