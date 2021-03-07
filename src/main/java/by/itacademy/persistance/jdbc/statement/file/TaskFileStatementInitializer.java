package by.itacademy.persistance.jdbc.statement.file;

import by.itacademy.model.file.File;
import by.itacademy.persistance.jdbc.statement.StatementInitializer;

import java.io.ByteArrayInputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TaskFileStatementInitializer implements StatementInitializer<File> {

    @Override
    public void processUpdateStatement(PreparedStatement stmt, File file) throws SQLException {
        byte[] bytes = file.getBytes();
        stmt.setBlob(1, new ByteArrayInputStream(bytes));
        stmt.setString(2, file.getName());
        stmt.setLong(3, file.getId());
    }

    @Override
    public void processCreateStatement(PreparedStatement stmt,File file) throws SQLException {
         processUpdateStatement(stmt, file);
    }
}
