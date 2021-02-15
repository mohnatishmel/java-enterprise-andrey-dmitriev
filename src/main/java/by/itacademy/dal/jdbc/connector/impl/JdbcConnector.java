package by.itacademy.dal.jdbc.connector.impl;

import by.itacademy.dal.jdbc.connector.Connector;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class JdbcConnector implements Connector {

    private static JdbcConnector instance;

    private static final String DATABASE_DRIVER;
    private static final String DATABASE_URL;
    private static final String DATABASE_USER;
    private static final String DATABASE_PASSWORD;

    static {
        try (InputStream resourceAsStream = JdbcConnector.class.getResourceAsStream("/database/data-source.properties")) {

            Properties properties = new Properties();
            properties.load(resourceAsStream);

            DATABASE_DRIVER = properties.getProperty("DATABASE_DRIVER_KEY");
            DATABASE_URL = properties.getProperty("DATABASE_URL_KEY");
            DATABASE_USER = properties.getProperty("DATABASE_USER_KEY");
            DATABASE_PASSWORD = properties.getProperty("DATABASE_PASSWORD_KEY");

            Class.forName(DATABASE_DRIVER);

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error initialization DataBaseConnector: " + e.getMessage());
            throw new RuntimeException("Error initialization DataBaseConnector: " + e.getMessage());
        }
    }

    private JdbcConnector() {
    }

    public static JdbcConnector getInstance() {
        if (instance == null) {
            instance = new JdbcConnector();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
    }

}
