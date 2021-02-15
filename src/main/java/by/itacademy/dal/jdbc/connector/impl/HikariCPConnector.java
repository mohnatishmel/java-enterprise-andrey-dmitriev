package by.itacademy.dal.jdbc.connector.impl;


import by.itacademy.dal.jdbc.connector.Connector;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

@Log4j2

public class HikariCPConnector implements Connector {

    private static HikariConfig CONFIG;
    private static HikariDataSource DATA_SOURCE;
    private static String CONFIG_FILE;

    private static HikariCPConnector instance = null;

    static {
        try {
            CONFIG_FILE = "/database/hikari.properties";
            CONFIG = new HikariConfig(CONFIG_FILE);
            DATA_SOURCE = new HikariDataSource(CONFIG);
            log.info("Hikari Connection pool initialized");
        } catch (Exception e) {
            log.debug("Error init connectionPool", Arrays.toString(e.getStackTrace()));
        }
    }

    public HikariCPConnector() {
    }

    @Override
    public Connection getConnection() throws SQLException {
        return DATA_SOURCE.getConnection();
    }

    public static HikariCPConnector getInstance() {
        if (instance == null) {
            instance = new HikariCPConnector();
        }
        return instance;
    }

}
