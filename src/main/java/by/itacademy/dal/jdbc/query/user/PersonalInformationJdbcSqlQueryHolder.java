package by.itacademy.dal.jdbc.query.user;

import by.itacademy.dal.jdbc.query.CrudJdbcSqlQueryHolder;

public class PersonalInformationJdbcSqlQueryHolder implements CrudJdbcSqlQueryHolder {

    private static final String GET_BY_ID_SQL = "SELECT personal_information_id, first_name, last_name  FROM personal_information WHERE personal_information_id = ?;";
    private static final String UPDATE_SQL = "UPDATE  personal_information SET first_name = ?, last_name = ? WHERE personal_information_id = ?;";
    private static final String CREATE_SQL = "INSERT INTO personal_information(first_name, last_name) VALUES(?,?);";
    private static final String DELETE_SQL = "DELETE FROM personal_information WHERE personal_information_id = ?;";

    @Override
    public String getByIdSql() {
        return GET_BY_ID_SQL;
    }

    @Override
    public String updateSql() {
        return UPDATE_SQL;
    }

    @Override
    public String createSql() {
        return CREATE_SQL;
    }

    @Override
    public String deleteSql() {
        return DELETE_SQL;
    }
}
