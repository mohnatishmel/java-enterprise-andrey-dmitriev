package by.itacademy.dal.jdbc.dao.user;

import by.itacademy.dal.jdbc.AbstractCrudJdbcDao;
import by.itacademy.dal.jdbc.connector.Connector;
import by.itacademy.dal.jdbc.mapper.ResultSetMapper;
import by.itacademy.dal.jdbc.mapper.user.CredentialResultSetMapper;
import by.itacademy.dal.jdbc.query.CrudJdbcSqlQueryHolder;
import by.itacademy.dal.jdbc.query.user.CredentialsJdbcSqlQueryHolder;
import by.itacademy.dal.jdbc.statement.StatementInitializer;
import by.itacademy.dal.jdbc.statement.user.CredentialStatementInitializer;
import by.itacademy.exception.dao.DaoException;
import by.itacademy.exception.security.authentication.UsernameNotFoundException;
import by.itacademy.model.user.Credential;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.util.Arrays;

@Log4j2

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

    public Credential getByLogin(String login) throws DaoException, UsernameNotFoundException {
        try(Connection connection = getConnector().getConnection()) {
            PreparedStatement ps = connection.prepareStatement(new CredentialsJdbcSqlQueryHolder().getByLoginSql());
            ps.setString(1, login);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return getResultSetMapper().processResultSet(rs);
                }
                String message = "login not found";
                log.debug(message);
                throw new UsernameNotFoundException(message);

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
}
