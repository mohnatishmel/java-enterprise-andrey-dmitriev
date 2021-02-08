package by.itacademy.dal.jdbc.mapper.impl.task;

import by.itacademy.dal.jdbc.mapper.AbstractResultSetMapper;
import by.itacademy.dal.jdbc.mapper.ResultSetMapper;
import by.itacademy.exception.DaoException;
import by.itacademy.model.task.TaskInformation;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskInformationResultSetMapper extends AbstractResultSetMapper<TaskInformation> {

    @Override
    public TaskInformation processResultSet(ResultSet rs) throws DaoException {
        try {
            int id = rs.getInt("task_information_id");
            String description = rs.getString("description");
            String filePath = rs.getString("file_path");

            return new TaskInformation(id, description, filePath);
        } catch (SQLException e) {
            throw new DaoException("Error mapping resultSet to an Object", e);
        }
    }
}
