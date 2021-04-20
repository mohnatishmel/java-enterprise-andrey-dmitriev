package by.itacademy;


import by.itacademy.constant.ApplicationConstant;
import by.itacademy.entities.task.Task;
import by.itacademy.entities.user.User;
import by.itacademy.exception.ApplicationBasedException;
import by.itacademy.exception.security.authentication.UsernameNotFoundException;
import by.itacademy.persistence.TaskDao;
import by.itacademy.persistence.UserDao;

import by.itacademy.service.FacadeService;
import by.itacademy.service.TaskService;
import by.itacademy.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.h2.tools.Server;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log4j2

@WebListener
public class ApplicationListener implements ServletContextListener {

    // for connect with UI tool to database use url - jdbc:h2:tcp://localhost/mem:jdbc
    private static Server SERVER;

    public void contextInitialized(ServletContextEvent event) {

        System.setProperty("log4j.configurationFile", "logger/log4j2.xml");

        try {
            SERVER = Server.createTcpServer().start();
            log.debug("H2 Server started");

        } catch (SQLException e) {
            e.printStackTrace();
            log.debug("Error starting H2 Server", e);
        }

//        FacadeService facadeService = ApplicationConstant.APPLICATION_CONTEXT.getBean(FacadeService.class);
//        TaskService taskService = ApplicationConstant.APPLICATION_CONTEXT.getBean(TaskService.class);
//        UserService userService = ApplicationConstant.APPLICATION_CONTEXT.getBean(UserService.class);
//
//        List<Task> tasks = new ArrayList<>();
//        try {
//            tasks = taskService.getTasksForUser(1);
//        } catch (ApplicationBasedException e) {
//            e.printStackTrace();
//        }
//        System.out.println(tasks);
//
//        User user = null;
//        try {
//            user = (User) userService.getById(1);
//        } catch (ApplicationBasedException e) {
//            e.printStackTrace();
//        }
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
