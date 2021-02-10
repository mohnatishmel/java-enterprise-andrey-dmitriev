package by.itacademy.dal.jdbc;


import by.itacademy.dal.jdbc.connector.Connector;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class BaseAbstractJdbcDao {

    private final Connector connector;

    public Connector getConnector() {
        return connector;
    }
}