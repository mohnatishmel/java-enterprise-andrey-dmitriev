package by.itacademy.dal.jdbc.statement.impl.task;

import by.itacademy.dal.jdbc.statement.StatementInitializer;
import by.itacademy.model.task.TaskInformation;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TaskInformationStatementInitializer implements StatementInitializer<TaskInformation> {

    @Override
    public void processStatement(PreparedStatement stmt, TaskInformation taskInformation) throws SQLException {
        stmt.setString(1, taskInformation.getDescription());
        stmt.setString(2, taskInformation.getFilepath());
    }
}
