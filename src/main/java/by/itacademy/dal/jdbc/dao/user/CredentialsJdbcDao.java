package by.itacademy.dal.jdbc.dao.user;

import by.itacademy.dal.jdbc.AbstractCrudJdbcDao;
import by.itacademy.dal.jdbc.connector.Connector;
import by.itacademy.dal.jdbc.mapper.ResultSetMapper;
import by.itacademy.dal.jdbc.mapper.user.CredentialResultSetMapper;
import by.itacademy.dal.jdbc.query.CrudJdbcSqlQueryHolder;
import by.itacademy.dal.jdbc.query.user.CredentialsJdbcSqlQueryHolder;
import by.itacademy.dal.jdbc.statement.StatementInitializer;
import by.itacademy.dal.jdbc.statement.user.CredentialStatementInitializer;
import by.itacademy.exception.DaoException;
import by.itacademy.model.user.Credential;

import java.sql.*;

public class CredentialsJdbcDao extends AbstractCrudJdbcDao<Credential> {

    public CredentialsJdbcDao(Connector connector) {
        super(connector);
    }

    @Override
    protected CrudJdbcSqlQueryHolder getSqlHolder() {
        return new CredentialsJdbcSqlQueryHolder();
    }

    @Override
    protected ResultSetMapper<Credential> getResultSetMapper() {
        return new CredentialResultSetMapper();
    }

    @Override
    protected StatementInitializer<Credential> getStatementInitializer() {
        return new CredentialStatementInitializer();
    }

    public Credential getByLogin(String login) throws DaoException {
        try(Connection connection = getConnector().getConnection()) {
            PreparedStatement ps = connection.prepareStatement(new CredentialsJdbcSqlQueryHolder().getByLoginSql());
            ps.setString(1, login);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return getResultSetMapper().processResultSet(rs);
                }
                throw new DaoException("Invalid login: " + login);

            } catch (SQLException e) {
                e.printStackTrace();
                throw new DaoException("Error process getById entity method: " + e.getMessage(), e);
            }
        } catch (SQLException e) {
            throw new DaoException("Error receive database connection: " + e.getMessage(), e);
        }
    }
}
