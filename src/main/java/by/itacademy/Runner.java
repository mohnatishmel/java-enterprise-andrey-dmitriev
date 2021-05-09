//package by.itacademy;
//
//import by.itacademy.constant.ApplicationConstant;
//import by.itacademy.entities.user.User;
//import by.itacademy.exception.ApplicationBasedException;
//
//import by.itacademy.entities.task.Task;
//
//import by.itacademy.service.FacadeService;
//import by.itacademy.service.TaskService;
//import by.itacademy.service.UserService;
//import org.h2.tools.Server;
//
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//
//public class Runner {
//
//    // for connect with UI tool to database use url - jdbc:h2:tcp://localhost/mem:jdbc
//
//    private static Server SERVER;
//
//    static {
//        System.setProperty("log4j.configurationFile", "logger/log4j2.xml");
//        try {
//            SERVER = Server.createTcpServer().start();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void main(String[] args) {
//
//        FacadeService facadeService = ApplicationConstant.APPLICATION_CONTEXT.getBean(FacadeService.class);
//
//        TaskService taskService = ApplicationConstant.APPLICATION_CONTEXT.getBean(TaskService.class);
////        SERVER.stop();
//    }
//}
