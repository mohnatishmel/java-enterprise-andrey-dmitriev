package by.itacademy.persistance.jdbc.mapper.file;

import by.itacademy.exception.dao.DaoException;
import by.itacademy.model.file.File;
import by.itacademy.persistance.jdbc.mapper.AbstractResultSetMapper;

import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskFileResultSetMapper extends AbstractResultSetMapper<File> {

    @Override
    public File processResultSet(ResultSet rs) throws DaoException {
        try {
            long id = rs.getLong("task_file_id");
            Blob blob = rs.getBlob("file");
            String name = rs.getString("file_name");
            byte[] bytes = blob.getBytes(1, (int) blob.length());

            return new File(id, bytes, name);
        } catch (SQLException e) {
            throw new DaoException("Error mapping resultSet to personal_information Object");
        }
    }
}
