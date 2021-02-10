package by.itacademy;

import by.itacademy.constant.ApplicationConstant;
import by.itacademy.dal.TaskDao;
import by.itacademy.dal.UserDao;
import by.itacademy.dal.jdbc.dao.task.TaskInformationJdbcDao;
import by.itacademy.dal.jdbc.dao.task.TaskJdbcDao;
import by.itacademy.dal.jdbc.dao.user.*;
import by.itacademy.dal.jdbc.connector.Connector;
import by.itacademy.dal.jdbc.connector.impl.HikariCPConnector;
import by.itacademy.exception.DaoException;
import by.itacademy.exception.UsernameNotFoundException;
import by.itacademy.model.task.Task;
import by.itacademy.model.user.User;
import org.h2.tools.RunScript;
import org.h2.tools.Server;


import java.io.FileReader;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Runner {

    // for connect with UI tool to database use url - jdbc:h2:tcp://localhost/mem:jdbc

    private static Server SERVER;
    private static Connector DATABASE_CONNECTOR;

    static {
        try {
            SERVER = Server.createTcpServer().start();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DATABASE_CONNECTOR = HikariCPConnector.getInstance();
        init();
    }

    private static void init() {
        try (Connection connection = DATABASE_CONNECTOR.getConnection()) {

            URL ddlSql = Runner.class.getResource(ApplicationConstant.DDL_INITIALIZATION_SCRIPT_PATH);
            URL dmlSql = Runner.class.getResource(ApplicationConstant.DML_INITIALIZATION_SCRIPT_PATH);

            RunScript.execute(connection, new FileReader(Paths.get(ddlSql.toURI()).toFile()));
            RunScript.execute(connection, new FileReader(Paths.get(dmlSql.toURI()).toFile()));
            connection.commit();

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error initialization in-memory database: " + e.getMessage());
        }
    }


    public static void main(String[] args) throws DaoException, UsernameNotFoundException {

        Connector connector = new HikariCPConnector();

        TaskInformationJdbcDao taskInformationDao = new TaskInformationJdbcDao(connector);
        TaskDao taskDao = new TaskJdbcDao(connector, taskInformationDao);

        PersonalInformationJdbcDao personalInformationDao = new PersonalInformationJdbcDao(connector);
        CredentialsJdbcDao credentialsDao = new CredentialsJdbcDao(connector);
        RoleJdbcDao roleDao = new RoleJdbcDao(connector);
        RolesMapJdbcDao rolesMapDao = new RolesMapJdbcDao(connector,roleDao);
        UserDao userDao = new UserJdbcDaoBasic(connector, credentialsDao, rolesMapDao, personalInformationDao, taskDao);


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
        SERVER.stop();
    }
}
