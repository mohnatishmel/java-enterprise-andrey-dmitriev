package by.itacademy;

import by.itacademy.constant.ApplicationConstant;
import by.itacademy.dal.TaskDao;
import by.itacademy.dal.UserDao;
import by.itacademy.dal.jdbc.connector.Connector;
import by.itacademy.dal.jdbc.connector.impl.HikariCPConnector;
import by.itacademy.dal.jdbc.dao.task.TaskInformationJdbcDao;
import by.itacademy.dal.jdbc.dao.task.TaskJdbcDao;
import by.itacademy.dal.jdbc.dao.user.*;
import by.itacademy.model.task.Task;
import by.itacademy.model.user.User;
import by.itacademy.security.SecurityConfigurer;
import by.itacademy.security.service.authentication.AuthenticationManager;
import by.itacademy.security.service.AuthenticationProvider;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.h2.tools.RunScript;
import org.h2.tools.Server;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.FileReader;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@Log4j2

@WebListener
public class ApplicationListener implements ServletContextListener {

    // for connect with UI tool to database use url - jdbc:h2:tcp://localhost/mem:jdbc

    private static Server SERVER;
    private static Connector DATABASE_CONNECTOR;

    @SneakyThrows
    public void contextInitialized(ServletContextEvent event) {

        System.setProperty("log4j.configurationFile", "logger/log4j2.xml");

        try {
            SERVER = Server.createTcpServer().start();
            log.debug("H2 Server started");

        } catch (SQLException e) {
            e.printStackTrace();
            log.debug("Error starting H2 Server", Arrays.toString(e.getStackTrace()));
        }

        DATABASE_CONNECTOR = HikariCPConnector.getInstance();
//        DATABASE_CONNECTOR = C3P0Connector.getInstance();

        try (Connection connection = DATABASE_CONNECTOR.getConnection()) {

            URL ddlSql = ApplicationListener.class.getResource(ApplicationConstant.DDL_INITIALIZATION_SCRIPT_PATH);
            URL dmlSql = ApplicationListener.class.getResource(ApplicationConstant.DML_INITIALIZATION_SCRIPT_PATH);

            RunScript.execute(connection, new FileReader(Paths.get(ddlSql.toURI()).toFile()));
            RunScript.execute(connection, new FileReader(Paths.get(dmlSql.toURI()).toFile()));
            connection.commit();

        } catch (Exception e) {
            e.printStackTrace();
            String message = "Error initialization in-memory database: ";
            log.debug(message, Arrays.toString(e.getStackTrace()));
            System.err.println(message + e.getMessage());
        }

        TaskInformationJdbcDao taskInformationDao = new TaskInformationJdbcDao(DATABASE_CONNECTOR);
        TaskDao taskDao = new TaskJdbcDao(DATABASE_CONNECTOR, taskInformationDao);

        PersonalInformationJdbcDao personalInformationDao = new PersonalInformationJdbcDao(DATABASE_CONNECTOR);
        CredentialsJdbcDao credentialsDao = new CredentialsJdbcDao(DATABASE_CONNECTOR);
        RoleJdbcDao roleDao = new RoleJdbcDao(DATABASE_CONNECTOR);
        RolesMapJdbcDao rolesMapDao = new RolesMapJdbcDao(DATABASE_CONNECTOR, roleDao);
        UserDao userDao = new UserJdbcDaoBasic(DATABASE_CONNECTOR, credentialsDao, rolesMapDao, personalInformationDao, taskDao);

        AuthenticationProvider authenticationProvider = new AuthenticationProvider(userDao);
        AuthenticationManager.getInstance().add(authenticationProvider);

        SecurityConfigurer.init();


        User user = (User) userDao.loadUserByUsername("user1");


        user = userDao.getById(4);
        System.out.println(user.toString());

        user.getCredential().setPassword("QWERTY");
        user = userDao.update(user);
        System.out.println(user.toString());

        userDao.delete(user.getId());

        List<Task> taskList = taskDao.getByUserId(1);
        System.out.println(taskList.toString());

        Task task = taskDao.getById(1);
        System.out.println(taskList.toString());
        //SERVER.stop();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
