package by.itacademy.dal.jdbc.query.user;

public class RoleJdbcSqlQueryHolder {

    private static final String GET_BY_ID_SQL = "SELECT role_id, role  FROM roles WHERE role_id = ?;";
    private static final String GET_BY_ROLE_NAME_SQL = "SELECT role_id, role  FROM roles WHERE role = ?;";

    public String getByIdSql() {
        return GET_BY_ID_SQL;
    }

    public String getGetByNameSql() {
        return GET_BY_ROLE_NAME_SQL;
    }
}
