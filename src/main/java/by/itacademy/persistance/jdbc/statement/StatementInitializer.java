package by.itacademy.persistance.jdbc.statement;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface StatementInitializer<T> {

    void processUpdateStatement(PreparedStatement stmt, T t) throws SQLException;

    void processCreateStatement(PreparedStatement stmt, T t) throws SQLException;
}
