package by.itacademy.dal.jdbc.query.task;

import by.itacademy.dal.jdbc.query.CrudJdbcSqlQueryHolder;

public class TaskInformationJdbcSqlQueryHolder implements CrudJdbcSqlQueryHolder {

    private static final String GET_BY_ID_SQL = "SELECT task_information_id, description, file_path  FROM task_information WHERE task_information_id = ?;";
    private static final String UPDATE_SQL = "UPDATE  task_information SET description = ?, file_path = ? WHERE task_information_id = ?;";
    private static final String CREATE_SQL = "INSERT INTO task_information(description, file_path) VALUES(?,?);";
    private static final String DELETE_SQL = "DELETE FROM task_information WHERE task_information_id = ?;";

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
