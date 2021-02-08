package by.itacademy.dal.jdbc;

import by.itacademy.dal.jdbc.mapper.ResultSetMapper;
import by.itacademy.dal.jdbc.query.CrudJdbcSqlQueryHolder;
import by.itacademy.dal.jdbc.statement.StatementInitializer;
import by.itacademy.exception.DaoException;
import lombok.AllArgsConstructor;

import java.sql.*;

@AllArgsConstructor

public abstract class AbstractCrudTransactionJdbcDao<T> implements CrudTransactionDao<T> {

    protected abstract CrudJdbcSqlQueryHolder getSqlHolder();

    protected abstract ResultSetMapper<T> getResultSetMapper();

    protected abstract StatementInitializer<T> getStatementInitializer();

    @Override
    public T getById(int id, Connection connection) throws DaoException {
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
    public T create(T t, Connection connection) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(getSqlHolder().createSql(), Statement.RETURN_GENERATED_KEYS);
            getStatementInitializer().processStatement(statement, t);
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


    public T update(T t, Connection connection) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(getSqlHolder().updateSql());
            getStatementInitializer().processStatement(statement, t);
            getStatementInitializer().processStatement(statement, t);
            statement.execute();

            return t;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Error process update entity method: " + e.getMessage(), e);
        }
    }


    public void delete(int id, Connection connection) throws DaoException {
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
