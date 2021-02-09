package by.itacademy.dal.jdbc;

import by.itacademy.dal.CrudDao;
import by.itacademy.dal.jdbc.connector.Connector;
import by.itacademy.exception.DaoException;
import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.SQLException;

@RequiredArgsConstructor
public abstract class AbstractCrudJdbcDao<T> implements CrudDao<T> {

    private final Connector connector;

    @Override
    public T getById(int id) throws DaoException {
        T t;
        try (Connection connection = connector.getConnection()) {
            try {
                t = getEntityById(id, connection);
                connection.commit();

            } catch (DaoException e) {
                connection.rollback();
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
        try (Connection connection = connector.getConnection()) {
            try {
                t = createEntity(t, connection);
                connection.commit();

            } catch (DaoException e) {
                connection.rollback();
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
        try (Connection connection = connector.getConnection()) {
            try {
                updateEntity(t, connection);
                connection.commit();

            } catch (DaoException e) {
                connection.rollback();
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
        try (Connection connection = connector.getConnection()) {
            try {
                deleteEntity(id, connection);
                connection.commit();

            } catch (DaoException e) {
                connection.rollback();
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

    protected Connector getConnector() {
        return connector;
    }
}
