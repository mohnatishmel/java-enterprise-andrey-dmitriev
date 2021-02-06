package by.itacademy;

import by.itacademy.constant.ApplicationConstant;
import by.itacademy.dal.jdbc.connector.Connector;
import by.itacademy.dal.jdbc.connector.HikariCPConnector;
import by.itacademy.dal.jdbc.task.TaskJdbcDao;
import by.itacademy.dal.jdbc.user.UserJdbcDao;
import by.itacademy.exception.DaoException;
import by.itacademy.exception.UsernameNotFoundException;
import by.itacademy.model.task.Task;
import by.itacademy.model.task.TaskInformation;
import by.itacademy.model.user.User;
import org.h2.tools.RunScript;
import org.h2.tools.Server;


import java.io.FileReader;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class Runner {
    // jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;INIT=runscript from 'classpath:/db.sql'


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


        TaskInformation taskInformation;

        //User user = UserJdbcDao.getInstance().getById(1);
        User user = (User) UserJdbcDao.getInstance().loadUserByUsername("user1");
        System.out.println(user.toString());

        user = (User) UserJdbcDao.getInstance().getById(4);
        System.out.println(user.toString());

        user.getCredential().setPassword("QWERTY");
        user = (User) UserJdbcDao.getInstance().update(user);
        System.out.println(user.toString());

        UserJdbcDao.getInstance().delete(user.getId());

        List<Task> taskList = TaskJdbcDao.getInstance().getByUserId(1);
        System.out.println(taskList.toString());

        Task task = TaskJdbcDao.getInstance().getById(1);
        System.out.println(taskList.toString());
        //SERVER.stop();
    }
}
