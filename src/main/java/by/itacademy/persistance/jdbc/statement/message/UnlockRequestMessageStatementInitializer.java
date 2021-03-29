package by.itacademy.persistance.jdbc.statement.message;

import by.itacademy.model.message.UnlockRequestMessage;
import by.itacademy.persistance.jdbc.statement.StatementInitializer;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UnlockRequestMessageStatementInitializer implements StatementInitializer<UnlockRequestMessage> {

    @Override
    public void processUpdateStatement(PreparedStatement stmt, UnlockRequestMessage message) throws SQLException {
        processCreateStatement(stmt, message);
        stmt.setInt(4, message.getId());
    }

    @Override
    public void processCreateStatement(PreparedStatement stmt, UnlockRequestMessage message) throws SQLException {
        stmt.setInt(1, message.getUserId());
        stmt.setString(2, message.getUserName());
        stmt.setString(3, message.getMessageBody());
    }
}
