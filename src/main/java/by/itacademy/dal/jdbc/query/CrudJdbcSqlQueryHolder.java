package by.itacademy.dal.jdbc.query;

public interface CrudJdbcSqlQueryHolder {
    String getByIdSql();
    String updateSql();
    String createSql();
    String deleteSql();
}
