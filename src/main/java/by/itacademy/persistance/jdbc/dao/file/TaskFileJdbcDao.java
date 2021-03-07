package by.itacademy.persistance.jdbc.dao.file;

import by.itacademy.exception.dao.DaoException;
import by.itacademy.model.file.File;
import by.itacademy.persistance.jdbc.AbstractCrudJdbcDao;
import by.itacademy.persistance.jdbc.connector.Connector;
import by.itacademy.persistance.jdbc.mapper.ResultSetMapper;
import by.itacademy.persistance.jdbc.mapper.file.TaskFileResultSetMapper;
import by.itacademy.persistance.jdbc.query.CrudJdbcSqlQueryHolder;
import by.itacademy.persistance.jdbc.query.file.TaskFileJdbcSqlQueryHolder;
import by.itacademy.persistance.jdbc.statement.StatementInitializer;
import by.itacademy.persistance.jdbc.statement.file.TaskFileStatementInitializer;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.util.Arrays;

@Log4j2

public class TaskFileJdbcDao extends AbstractCrudJdbcDao<File> {

    private static TaskFileJdbcDao instance;

    public TaskFileJdbcDao(Connector connector) {
        super(connector);
        instance = this;
    }

    @Override
    protected File createEntity(File file, Connection connection) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(getSqlHolder().createSql(), Statement.RETURN_GENERATED_KEYS);
            getStatementInitializer().processCreateStatement(statement, file);
            statement.executeUpdate();

            log.info("createEntity method called in " + this.getClass() );
            return file;

        } catch (SQLException e) {
            e.printStackTrace();
            String message = "Error process create entity: ";
            log.debug(message, Arrays.toString(e.getStackTrace()));
            throw new DaoException(message + e.getMessage(), e);
        }
    }

    @Override
    protected CrudJdbcSqlQueryHolder getSqlHolder() {
        return new TaskFileJdbcSqlQueryHolder();
    }

    @Override
    protected ResultSetMapper<File> getResultSetMapper() {
        return new TaskFileResultSetMapper();
    }

    @Override
    protected StatementInitializer<File> getStatementInitializer() {
        return new TaskFileStatementInitializer();
    }

    public static TaskFileJdbcDao getInstance() {
        return instance;
    }

}
