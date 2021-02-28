package by.itacademy.persistance.jdbc.statement.user;

import by.itacademy.persistance.jdbc.statement.StatementInitializer;
import by.itacademy.model.user.PersonalInformation;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PersonalInformationStatementInitializer  implements StatementInitializer<PersonalInformation> {

    @Override
    public void processUpdateStatement(PreparedStatement stmt, PersonalInformation personalInformation) throws SQLException {
        processCreateStatement(stmt, personalInformation);
        stmt.setInt(3, personalInformation.getId());
    }

    @Override
    public void processCreateStatement(PreparedStatement stmt, PersonalInformation personalInformation) throws SQLException {
        stmt.setString(1, personalInformation.getFirstName());
        stmt.setString(2, personalInformation.getLastName());
    }
}
