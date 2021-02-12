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
import by.itacademy.security.service.authentication.provider.LoginPasswordAuthenticationProvider;
import lombok.SneakyThrows;
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
import java.util.List;

@WebListener
public class ApplicationListener implements ServletContextListener {

    // for connect with UI tool to database use url - jdbc:h2:tcp://localhost/mem:jdbc

    private static Server SERVER;
    private static Connector DATABASE_CONNECTOR;

    @SneakyThrows
    public void contextInitialized(ServletContextEvent event) {

//        try {
//            SERVER = Server.createTcpServer().start();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        DATABASE_CONNECTOR = HikariCPConnector.getInstance();

        try (Connection connection = DATABASE_CONNECTOR.getConnection()) {

            URL ddlSql = ApplicationListener.class.getResource(ApplicationConstant.DDL_INITIALIZATION_SCRIPT_PATH);
            URL dmlSql = ApplicationListener.class.getResource(ApplicationConstant.DML_INITIALIZATION_SCRIPT_PATH);

            RunScript.execute(connection, new FileReader(Paths.get(ddlSql.toURI()).toFile()));
            RunScript.execute(connection, new FileReader(Paths.get(dmlSql.toURI()).toFile()));
            connection.commit();

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error initialization in-memory database: " + e.getMessage());
        }

        Connector connector = new HikariCPConnector();

        TaskInformationJdbcDao taskInformationDao = new TaskInformationJdbcDao(connector);
        TaskDao taskDao = new TaskJdbcDao(connector, taskInformationDao);

        PersonalInformationJdbcDao personalInformationDao = new PersonalInformationJdbcDao(connector);
        CredentialsJdbcDao credentialsDao = new CredentialsJdbcDao(connector);
        RoleJdbcDao roleDao = new RoleJdbcDao(connector);
        RolesMapJdbcDao rolesMapDao = new RolesMapJdbcDao(connector, roleDao);
        UserDao userDao = new UserJdbcDaoBasic(connector, credentialsDao, rolesMapDao, personalInformationDao, taskDao);

        LoginPasswordAuthenticationProvider loginPasswordAuthenticationProvider = new LoginPasswordAuthenticationProvider(userDao);
        AuthenticationManager.getInstance().add(loginPasswordAuthenticationProvider);

        SecurityConfigurer.init();


        User user = (User) userDao.loadUserByUsername("user1");
        System.out.println(user.toString());

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
