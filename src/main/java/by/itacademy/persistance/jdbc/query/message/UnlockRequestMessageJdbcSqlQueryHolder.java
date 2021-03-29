package by.itacademy.persistance.jdbc.query.message;

import by.itacademy.persistance.jdbc.query.CrudJdbcSqlQueryHolder;

public class UnlockRequestMessageJdbcSqlQueryHolder implements CrudJdbcSqlQueryHolder {

    private static final String GET_BY_ID_SQL = "SELECT message_id, user_id, user_name, message_body FROM unlock_request_messages WHERE message_id = ?;";
    private static final String GET_ALL_SQL = "SELECT message_id, user_id, user_name, message_body FROM unlock_request_messages;";
    private static final String GET_BY_USER_ID_SQL = "SELECT message_id, user_id, user_name, message_body FROM unlock_request_messages WHERE user_id = ?;";
    private static final String UPDATE_SQL = "UPDATE  unlock_request_messages SET user_id = ?, user_name = ?, message_body = ? WHERE message_id = ?;";
    private static final String CREATE_SQL = "INSERT INTO unlock_request_messages(user_id, user_name, message_body) VALUES(?,?,?);";
    private static final String DELETE_SQL = "DELETE FROM unlock_request_messages WHERE message_id = ?;";

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

    public String getGetByUserIdSql() {
        return GET_BY_USER_ID_SQL;
    }

    public String getAll() {
        return GET_ALL_SQL;
    }
}

