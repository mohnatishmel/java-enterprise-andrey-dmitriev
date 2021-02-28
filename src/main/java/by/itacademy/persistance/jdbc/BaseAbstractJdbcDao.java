package by.itacademy.persistance.jdbc;


import by.itacademy.persistance.jdbc.connector.Connector;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class BaseAbstractJdbcDao {

    private final Connector connector;

    public Connector getConnector() {
        return connector;
    }
}