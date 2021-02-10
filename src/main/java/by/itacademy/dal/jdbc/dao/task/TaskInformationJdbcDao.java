package by.itacademy.dal.jdbc.dao.task;

import by.itacademy.dal.jdbc.AbstractCrudJdbcDao;
import by.itacademy.dal.jdbc.connector.Connector;
import by.itacademy.dal.jdbc.mapper.ResultSetMapper;
import by.itacademy.dal.jdbc.mapper.task.TaskInformationResultSetMapper;
import by.itacademy.dal.jdbc.query.CrudJdbcSqlQueryHolder;
import by.itacademy.dal.jdbc.query.task.TaskInformationJdbcSqlQueryHolder;
import by.itacademy.dal.jdbc.statement.StatementInitializer;
import by.itacademy.dal.jdbc.statement.task.TaskInformationStatementInitializer;
import by.itacademy.model.task.TaskInformation;


public class TaskInformationJdbcDao extends AbstractCrudJdbcDao<TaskInformation> {

    public TaskInformationJdbcDao(Connector connector) {
        super(connector);
    }

    @Override
    protected CrudJdbcSqlQueryHolder getSqlHolder() {
        return new TaskInformationJdbcSqlQueryHolder();
    }

    @Override
    protected ResultSetMapper<TaskInformation> getResultSetMapper() {
        return new TaskInformationResultSetMapper();
    }

    @Override
    protected StatementInitializer<TaskInformation> getStatementInitializer() {
        return new TaskInformationStatementInitializer();
    }
}