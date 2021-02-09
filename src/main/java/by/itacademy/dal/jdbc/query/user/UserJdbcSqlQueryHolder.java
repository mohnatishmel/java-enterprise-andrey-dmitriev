package by.itacademy.dal.jdbc.query.user;

public class UserJdbcSqlQueryHolder {

    private static final String GET_BY_ID_SQL = "SELECT user_id, credentials_id, personal_information_id, profile_enable  FROM users WHERE user_id = ?;";
    private static final String GET_ALL_SQL = "SELECT user_id, credentials_id, personal_information_id, profile_enable   FROM users;";
    private static final String UPDATE_SQL = "UPDATE  users SET profile_enable = ?  WHERE user_id = ?;";
    private static final String CREATE_SQL = "INSERT INTO users(SELECT credentials_id, personal_information_id, profile_enable) VALUES(?,?,?);";
    private static final String DELETE_SQL = "DELETE FROM users WHERE user_id = ?;";
    private static final String GET_BY_NAME_SQL = "SELECT user_id, credentials_id, personal_information_id, profile_enable  FROM users WHERE credentials_id = ?;";

    public String getByIdSql() {
        return GET_BY_ID_SQL;
    }

    public String updateSql() {
        return UPDATE_SQL;
    }

    public String createSql() {
        return CREATE_SQL;
    }

    public String deleteSql() {
        return DELETE_SQL;
    }

    public String getAllSql() {
        return GET_ALL_SQL;
    }

    public String getGetByNameSql() {
        return GET_BY_NAME_SQL;
    }

}
