package by.itacademy.persistance.jdbc.query.task;

public class TaskJdbcSqlQueryHolder {

    private static final String GET_BY_USER_ID = "SELECT task_id, user_id, description, task_name, deadline, fixed, in_basket  FROM tasks WHERE user_id = ?;";
    private static final String GET_BY_ID_SQL = "SELECT task_id, user_id, description, task_name, deadline, fixed, in_basket  FROM tasks WHERE task_id = ?;";
    //    private static final String GET_ALL_SQL = "SELECT task_id user_id, description, task_name, deadline, fixed, in_basket  FROM tasks;";
    private static final String UPDATE_SQL = "UPDATE  tasks SET user_id = ?, description = ?, task_name = ?, deadline = ?, fixed = ?, in_basket = ? WHERE task_id = ?;";
    private static final String CREATE_SQL = "INSERT INTO tasks(user_id, description, task_name, deadline, fixed, in_basket) VALUES(?,?,?,?,?);";
    private static final String DELETE_SQL = "DELETE FROM tasks WHERE task_id = ?;";
    private static final String DELETE_BY_USER_ID_SQL = "DELETE FROM tasks WHERE user_id = ?;";

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

    public String getGetByUserId() {
        return GET_BY_USER_ID;
    }

    public String getDeleteByUserIdSql() {
        return DELETE_BY_USER_ID_SQL;
    }
}
