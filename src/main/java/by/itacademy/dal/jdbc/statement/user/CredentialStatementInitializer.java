package by.itacademy.dal.jdbc.statement.user;

import by.itacademy.dal.jdbc.statement.StatementInitializer;
import by.itacademy.model.user.Credential;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CredentialStatementInitializer implements StatementInitializer<Credential> {

    @Override
    public void processUpdateStatement(PreparedStatement stmt, Credential credential) throws SQLException {
        stmt.setString(1, credential.getPassword());
        stmt.setInt(2,credential.getId());
    }

    @Override
    public void processCreateStatement(PreparedStatement stmt, Credential credential) throws SQLException {
        stmt.setString(1, credential.getLogin());
        stmt.setString(2, credential.getPassword());
    }
}
