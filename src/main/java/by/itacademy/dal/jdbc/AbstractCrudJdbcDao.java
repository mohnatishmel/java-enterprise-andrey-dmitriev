package by.itacademy.dal.jdbc;

import by.itacademy.dal.CrudDao;
import by.itacademy.dal.jdbc.connector.Connector;
import by.itacademy.dal.jdbc.mapper.ResultSetMapper;
import by.itacademy.dal.jdbc.query.CrudJdbcSqlQueryHolder;
import by.itacademy.dal.jdbc.statement.StatementInitializer;
import by.itacademy.exception.DaoException;

import java.sql.*;


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
                    return getResultSetMapper().processResultSet(rs);
                }
                throw new DaoException("Invalid entity id: " + id);

            } catch (SQLException e) {
                e.printStackTrace();
                throw new DaoException("Error process getById entity method: " + e.getMessage(), e);
            }
        } catch (SQLException e) {
            throw new DaoException("Error receive database connection: " + e.getMessage(), e);
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
                return getResultSetMapper().processResultSet(rs, t);
            }

            throw new DaoException("Error generate ID for create entity: " + t);
        } catch (SQLException | DaoException e) {
            e.printStackTrace();
            throw new DaoException("Error process create entity: " + e.getMessage(), e);
        }
    }

    @Override
    protected T updateEntity(T t, Connection connection) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(getSqlHolder().updateSql());
            getStatementInitializer().processUpdateStatement(statement, t);
            statement.execute();

            return t;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Error process update entity method: " + e.getMessage(), e);
        }
    }

    @Override
    protected void deleteEntity(int id, Connection connection) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(getSqlHolder().deleteSql());
            statement.setInt(1, id);
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Error process delete entity method: " + e.getMessage());
        }
    }
}
