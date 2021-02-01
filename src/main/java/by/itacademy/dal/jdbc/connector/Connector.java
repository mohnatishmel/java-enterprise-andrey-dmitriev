package by.itacademy.dal.jdbc.connector;


import java.sql.Connection;
import java.sql.SQLException;

public interface Connector {

    Connection getConnection() throws SQLException;
}
