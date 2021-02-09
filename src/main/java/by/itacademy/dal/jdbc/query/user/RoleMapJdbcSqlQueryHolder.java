package by.itacademy.dal.jdbc.query.user;

public class RoleMapJdbcSqlQueryHolder {

    private static final String GET_BY_USER_ID_SQL = "SELECT role_id FROM roles_map WHERE user_id = ?;";
    private static final String CREATE_SQL = "INSERT INTO roles_map(user_id, role_id) VALUES(?,?);";
    private static final String DELETE_SQL = "DELETE FROM roles_map WHERE user_id = ?;";

    public String getByUserIdSql() {
        return GET_BY_USER_ID_SQL;
    }

    public String createSql() {
        return CREATE_SQL;
    }

    public String deleteSql() {
        return DELETE_SQL;
    }
}
