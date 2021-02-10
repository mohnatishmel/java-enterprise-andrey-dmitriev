package by.itacademy.dal.jdbc.dao.user;

import by.itacademy.dal.jdbc.AbstractCrudJdbcDao;
import by.itacademy.dal.jdbc.connector.Connector;
import by.itacademy.dal.jdbc.mapper.ResultSetMapper;
import by.itacademy.dal.jdbc.mapper.user.PersonalInformationResultSetMapper;
import by.itacademy.dal.jdbc.query.CrudJdbcSqlQueryHolder;
import by.itacademy.dal.jdbc.query.user.PersonalInformationJdbcSqlQueryHolder;
import by.itacademy.dal.jdbc.statement.StatementInitializer;
import by.itacademy.dal.jdbc.statement.user.PersonalInformationStatementInitializer;
import by.itacademy.model.user.PersonalInformation;

public class PersonalInformationJdbcDao extends AbstractCrudJdbcDao<PersonalInformation> {

    public PersonalInformationJdbcDao(Connector connector) {
        super(connector);
    }

    @Override
    protected CrudJdbcSqlQueryHolder getSqlHolder() {
        return new PersonalInformationJdbcSqlQueryHolder();
    }

    @Override
    protected ResultSetMapper<PersonalInformation> getResultSetMapper() {
        return new PersonalInformationResultSetMapper();
    }

    @Override
    protected StatementInitializer<PersonalInformation> getStatementInitializer() {
        return new PersonalInformationStatementInitializer();
    }
}
