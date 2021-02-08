package by.itacademy.dal.jdbc.statement;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface StatementInitializer<T> {
    void processStatement(PreparedStatement stmt, T t) throws SQLException;
}
