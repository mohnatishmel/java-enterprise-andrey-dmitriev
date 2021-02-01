package by.itacademy.dal.jdbc.connector;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class HikariCPConnector implements Connector {

    private static final HikariConfig CONFIG;
    private static final HikariDataSource DATA_SOURCE;
    private static final String CONFIG_FILE;

    static {
        CONFIG_FILE = "src/main/resources/database/hikari.properties";
        CONFIG = new HikariConfig(CONFIG_FILE);
        DATA_SOURCE = new HikariDataSource(CONFIG);
    }

    public HikariCPConnector() {
    }

    @Override
    public Connection getConnection() throws SQLException {
        return DATA_SOURCE.getConnection();
    }
}
