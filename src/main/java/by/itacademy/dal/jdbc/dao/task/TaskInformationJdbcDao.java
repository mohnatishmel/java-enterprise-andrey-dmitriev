package by.itacademy.dal.jdbc.dao.task;

import by.itacademy.dal.jdbc.AbstractCrudTransactionJdbcDao;
import by.itacademy.dal.jdbc.mapper.ResultSetMapper;
import by.itacademy.dal.jdbc.mapper.impl.task.TaskInformationResultSetMapper;
import by.itacademy.dal.jdbc.query.CrudJdbcSqlQueryHolder;
import by.itacademy.dal.jdbc.query.impl.task.TaskInformationJdbcSqlQueryHolder;
import by.itacademy.dal.jdbc.statement.StatementInitializer;
import by.itacademy.dal.jdbc.statement.impl.task.TaskInformationStatementInitializer;
import by.itacademy.model.task.TaskInformation;


public class TaskInformationJdbcDao extends AbstractCrudTransactionJdbcDao<TaskInformation> {


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