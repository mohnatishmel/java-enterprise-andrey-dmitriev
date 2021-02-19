package by.itacademy.dal.jdbc;

import by.itacademy.dal.CrudDao;
import by.itacademy.dal.jdbc.connector.Connector;
import by.itacademy.dal.jdbc.mapper.ResultSetMapper;
import by.itacademy.dal.jdbc.query.CrudJdbcSqlQueryHolder;
import by.itacademy.dal.jdbc.statement.StatementInitializer;
import by.itacademy.exception.dao.DaoException;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.util.Arrays;

@Log4j2

public abstract class AbstractCrudJdbcDao<T> extends AbstractBasicCrudJdbcDao<T> implements CrudDao<T> {

    protected abstract CrudJdbcSqlQueryHolder getSqlHolder();

    protected abstract ResultSetMapper<T> getResultSetMapper();

    protected abstract StatementInitializer<T> getStatementInitializer();

    public AbstractCrudJdbcDao(Connector connector) {
        super(connector);
    }


    @Override
    protected T getEntityById(int id, Connection connection) throws DaoException {
        try {
            PreparedStatement ps = connection.prepareStatement(getSqlHolder().getByIdSql());
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    log.info("getEntityById method called in " + this.getClass() );
                    return getResultSetMapper().processResultSet(rs);
                }
                throw new DaoException("Invalid entity id: " + id);

            } catch (SQLException e) {
                e.printStackTrace();
                String message = "Error process getById entity method: ";
                log.debug(message, Arrays.toString(e.getStackTrace()));
                throw new DaoException(message + e.getMessage(), e);
            }
        } catch (SQLException e) {
            String message = "Error receive database connection: ";
            log.debug(message, Arrays.toString(e.getStackTrace()));
            throw new DaoException(message + e.getMessage(), e);
        }
    }

    @Override
    protected T createEntity(T t, Connection connection) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(getSqlHolder().createSql(), Statement.RETURN_GENERATED_KEYS);
            getStatementInitializer().processCreateStatement(statement, t);
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                log.info("createEntity method called in " + this.getClass() );
                return getResultSetMapper().processResultSet(rs, t);
            }

            String message = "Error generate ID for create entity: ";
            log.debug(message + t);
            throw new DaoException(message + t);
        } catch (SQLException | DaoException e) {
            e.printStackTrace();
            String message = "Error process create entity: ";
            log.debug(message, Arrays.toString(e.getStackTrace()));
            throw new DaoException(message + e.getMessage(), e);
        }
    }

    @Override
    protected T updateEntity(T t, Connection connection) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(getSqlHolder().updateSql());
            getStatementInitializer().processUpdateStatement(statement, t);
            statement.execute();

            log.info("updateEntity method called in " + this.getClass() );
            return t;

        } catch (SQLException e) {
            e.printStackTrace();
            String message = "Error process update entity method: ";
            log.debug(message, Arrays.toString(e.getStackTrace()));
            throw new DaoException(message + e.getMessage(), e);
        }
    }

    @Override
    protected void deleteEntity(int id, Connection connection) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(getSqlHolder().deleteSql());
            statement.setInt(1, id);
            statement.execute();
            log.info("deleteEntity method called in " + this.getClass() );

        } catch (SQLException e) {
            e.printStackTrace();
            String message = "Error process delete entity method: ";
            log.debug(message, Arrays.toString(e.getStackTrace()));
            throw new DaoException(message + e.getMessage());
        }
    }
}
