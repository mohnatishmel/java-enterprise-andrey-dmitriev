package by.itacademy.dal.jdbc.connector;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class C3P0Connector implements Connector {

    private final static String JDBC_USERNAME = "root";
    private final static String JDBC_PASSWORD = "root";
    private final static String JDBC_URL = "jdbc:h2:d:\\info\\it_academy\\JD2_Winter_2020\\05 JDBC\\database\\db";
    private final static String DATABASE_DRIVER_NAME = "org.h2.Driver";

    private final int maxStatements = 130;
    private final int maxStatementsPerConnection = 10;
    private final int minPoolSize = 5;
    private final int acquireIncrement = 2;
    private final int maxPoolSize = 25;

    private static C3P0Connector instance = null;

    private DataSource dataSource;

    private C3P0Connector() {
        try {
            ComboPooledDataSource cpds = new ComboPooledDataSource();
            cpds.setDriverClass(DATABASE_DRIVER_NAME);
            cpds.setJdbcUrl(JDBC_URL);
            cpds.setUser(JDBC_USERNAME);
            cpds.setPassword(JDBC_PASSWORD);

            cpds.setMaxStatements(maxStatements);
            cpds.setMaxStatementsPerConnection(maxStatementsPerConnection);
            cpds.setMinPoolSize(minPoolSize);
            cpds.setAcquireIncrement(acquireIncrement);
            cpds.setMaxPoolSize(maxPoolSize);

            this.dataSource = cpds;
        } catch (PropertyVetoException ex) {
            System.err.println("Error init connectionPool " + ex);
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
            return dataSource.getConnection();
    }

    public static C3P0Connector getInstance() {
        if (instance == null) {
            instance = new C3P0Connector();
        }
        return instance;
    }

    public void closeResultSet(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException ignore) {
                // NOP
            }
        }
    }

    public void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException ignore) {
                // NOP
            }
        }
    }

    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ignore) {
                // NOP
            }
        }
    }
}
