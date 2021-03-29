package by.itacademy.persistance.jdbc.statement.file;

import by.itacademy.model.file.File;
import by.itacademy.persistance.jdbc.statement.StatementInitializer;
import lombok.extern.log4j.Log4j2;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

@Log4j2

public class TaskFileStatementInitializer implements StatementInitializer<File> {

    @Override
    public void processUpdateStatement(PreparedStatement stmt, File file) throws SQLException {
        byte[] bytes = file.getBytes();
        ByteArrayInputStream byteArrayIS = new ByteArrayInputStream(bytes);
        stmt.setBlob(1, byteArrayIS);
        stmt.setString(2, file.getName());
        stmt.setLong(3, file.getId());
        try {
            byteArrayIS.close();
        } catch (IOException e) {
            log.debug("Enable to close input stream", Arrays.toString(e.getStackTrace()));
        }
    }

    @Override
    public void processCreateStatement(PreparedStatement stmt,File file) throws SQLException {
         processUpdateStatement(stmt, file);
    }
}
