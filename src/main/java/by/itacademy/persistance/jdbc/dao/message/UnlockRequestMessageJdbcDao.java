package by.itacademy.persistance.jdbc.dao.message;

import by.itacademy.exception.dao.DaoException;
import by.itacademy.model.message.UnlockRequestMessage;
import by.itacademy.model.task.Task;
import by.itacademy.persistance.jdbc.AbstractCrudJdbcDao;
import by.itacademy.persistance.jdbc.connector.Connector;
import by.itacademy.persistance.jdbc.mapper.ResultSetMapper;
import by.itacademy.persistance.jdbc.mapper.message.UnlockRequestMessageResultSetMapper;
import by.itacademy.persistance.jdbc.query.CrudJdbcSqlQueryHolder;
import by.itacademy.persistance.jdbc.query.message.UnlockRequestMessageJdbcSqlQueryHolder;
import by.itacademy.persistance.jdbc.statement.StatementInitializer;
import by.itacademy.persistance.jdbc.statement.message.UnlockRequestMessageStatementInitializer;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Log4j2

public class UnlockRequestMessageJdbcDao  extends AbstractCrudJdbcDao<UnlockRequestMessage> {

    private static UnlockRequestMessageJdbcDao instance;

    public UnlockRequestMessageJdbcDao(Connector connector) {
        super(connector);
        instance = this;
    }

    @Override
    protected CrudJdbcSqlQueryHolder getSqlHolder() {
        return new UnlockRequestMessageJdbcSqlQueryHolder();
    }

    @Override
    protected ResultSetMapper<UnlockRequestMessage> getResultSetMapper() {
        return new UnlockRequestMessageResultSetMapper();
    }

    @Override
    protected StatementInitializer<UnlockRequestMessage> getStatementInitializer() {
        return new UnlockRequestMessageStatementInitializer();
    }

    public List<UnlockRequestMessage> getAll() throws DaoException {
        List<UnlockRequestMessage> messageList;
        try (Connection connection = getConnector().getConnection()) {
            try {
                messageList = getAllMessages(connection);
            } catch (DaoException e) {
                throw new DaoException(e);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            String message = "Error receive database connection: ";
            log.debug(message, Arrays.toString(e.getStackTrace()));
            throw new DaoException(message + e.getMessage(), e);
        }
        return messageList;
    }


    private List<UnlockRequestMessage> getAllMessages(Connection connection) throws DaoException {
        try {
            String query = ((UnlockRequestMessageJdbcSqlQueryHolder)getSqlHolder()).getAll();
            PreparedStatement ps = connection.prepareStatement(query);
            List<UnlockRequestMessage> messageList = new ArrayList<>();
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    messageList.add(getResultSetMapper().processResultSet(rs));
                }
                return messageList;

            } catch (SQLException e) {
                e.printStackTrace();
                throw new DaoException("Error process getById entity method: " + e.getMessage(), e);
            }
        } catch (SQLException e) {
            throw new DaoException("Error initialize prepared statement: " + e.getMessage(), e);
        }
    }


    public static UnlockRequestMessageJdbcDao getInstance() {
        return instance;
    }
}
