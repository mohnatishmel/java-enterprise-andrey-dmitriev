package by.itacademy;

import by.itacademy.constant.ApplicationConstant;
import by.itacademy.entities.user.User;
import by.itacademy.exception.ApplicationBasedException;

import by.itacademy.exception.dao.DaoException;
import by.itacademy.exception.security.authentication.UsernameNotFoundException;
import by.itacademy.entities.task.Task;

import by.itacademy.service.FacadeService;
import by.itacademy.service.TaskService;
import by.itacademy.service.UserService;
import org.h2.tools.Server;

import java.sql.SQLException;
import java.util.ArrayList;
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

        FacadeService facadeService = ApplicationConstant.APPLICATION_CONTEXT.getBean(FacadeService.class);

        TaskService taskService = ApplicationConstant.APPLICATION_CONTEXT.getBean(TaskService.class);
        UserService userService = ApplicationConstant.APPLICATION_CONTEXT.getBean(UserService.class);


        List<Task> tasks = new ArrayList<>();
        try {
            tasks = taskService.getTasksForUser(1);
        } catch (ApplicationBasedException e) {
            e.printStackTrace();
        }
        System.out.println(tasks);

        User user = null;
        try {
            user = (User) userService.getById(1);
        } catch (ApplicationBasedException e) {
            e.printStackTrace();
        }
        System.out.println(user.toString());

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

        SERVER.stop();
    }
}
