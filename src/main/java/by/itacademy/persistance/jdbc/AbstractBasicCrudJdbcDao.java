package by.itacademy.persistance.jdbc;

import by.itacademy.persistance.CrudDao;
import by.itacademy.persistance.jdbc.connector.Connector;
import by.itacademy.exception.dao.DaoException;

import java.sql.Connection;
import java.sql.SQLException;


public abstract class AbstractBasicCrudJdbcDao<T> extends BaseAbstractJdbcDao implements CrudDao<T> {

    public AbstractBasicCrudJdbcDao(Connector connector) {
        super(connector);
    }

    @Override
    public T getById(int id) throws DaoException {
        T t;
        try (Connection connection = getConnector().getConnection()) {
            try {
                t = getEntityById(id, connection);

            } catch (DaoException e) {
                throw new DaoException(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Error receive database connection: " + e.getMessage(), e);
        }
        return t;
    }

    @Override
    public T create(T t) throws DaoException {
        try (Connection connection = getConnector().getConnection()) {
            try {
                t = createEntity(t, connection);

            } catch (DaoException e) {
                throw new DaoException(e);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Error receive database connection: " + e.getMessage(), e);
        }
        return t;
    }

    @Override
    public T update(T t) throws DaoException {
        try (Connection connection = getConnector().getConnection()) {
            try {
                updateEntity(t, connection);

            } catch (DaoException e) {
                throw new DaoException(e);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Error receive database connection: " + e.getMessage(), e);
        }
        return t;
    }

    @Override
    public void delete(int id) throws DaoException {
        try (Connection connection = getConnector().getConnection()) {
            try {
                deleteEntity(id, connection);

            } catch (DaoException e) {
                throw new DaoException(e);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Error receive database connection: " + e.getMessage(), e);
        }
    }

    protected abstract T getEntityById(int id, Connection connection) throws DaoException;

    protected abstract T createEntity(T t, Connection connection) throws DaoException;

    protected abstract T updateEntity(T t, Connection connection) throws DaoException;

    protected abstract void deleteEntity(int id, Connection connection) throws DaoException;
}
