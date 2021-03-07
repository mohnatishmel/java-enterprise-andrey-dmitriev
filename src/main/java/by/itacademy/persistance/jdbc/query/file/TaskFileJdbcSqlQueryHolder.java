package by.itacademy.persistance.jdbc.query.file;

import by.itacademy.persistance.jdbc.query.CrudJdbcSqlQueryHolder;

public class TaskFileJdbcSqlQueryHolder implements CrudJdbcSqlQueryHolder {

    private static final String GET_BY_ID_SQL = "SELECT task_file_id, file, file_name FROM task_files WHERE task_file_id = ?;";
    private static final String UPDATE_SQL = "UPDATE  task_files SET file = ?, file_name = ? WHERE task_file_id = ?;";
    private static final String CREATE_SQL = "INSERT INTO task_files(file, file_name, task_file_id) VALUES(?,?,?);";
    private static final String DELETE_SQL = "DELETE FROM task_files WHERE task_file_id = ?;";

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
}

