package by.itacademy.persistance.jdbc.dao.user;

import by.itacademy.persistance.jdbc.AbstractCrudJdbcDao;
import by.itacademy.persistance.jdbc.connector.Connector;
import by.itacademy.persistance.jdbc.mapper.ResultSetMapper;
import by.itacademy.persistance.jdbc.mapper.user.PersonalInformationResultSetMapper;
import by.itacademy.persistance.jdbc.query.CrudJdbcSqlQueryHolder;
import by.itacademy.persistance.jdbc.query.user.PersonalInformationJdbcSqlQueryHolder;
import by.itacademy.persistance.jdbc.statement.StatementInitializer;
import by.itacademy.persistance.jdbc.statement.user.PersonalInformationStatementInitializer;
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
