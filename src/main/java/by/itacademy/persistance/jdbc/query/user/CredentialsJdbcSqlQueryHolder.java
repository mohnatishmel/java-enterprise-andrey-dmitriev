package by.itacademy.persistance.jdbc.query.user;

import by.itacademy.persistance.jdbc.query.CrudJdbcSqlQueryHolder;

public class CredentialsJdbcSqlQueryHolder implements CrudJdbcSqlQueryHolder {

    private static final String GET_BY_ID_SQL = "SELECT credentials_id, login, password  FROM credentials WHERE credentials_id = ?;";
    private static final String GET_BY_LOGIN_SQL = "SELECT credentials_id, login, password  FROM credentials WHERE login = ?;";
    private static final String UPDATE_SQL = "UPDATE  credentials SET password = ? WHERE credentials_id = ?;";
    private static final String CREATE_SQL = "INSERT INTO credentials(login, password) VALUES(?,?);";
    private static final String DELETE_SQL = "DELETE FROM credentials WHERE credentials_id = ?;";

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

    public String getByLoginSql() {
        return GET_BY_LOGIN_SQL;
    }
}
