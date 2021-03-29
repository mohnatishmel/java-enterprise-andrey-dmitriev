package by.itacademy.persistance.jdbc.mapper.message;

import by.itacademy.exception.dao.DaoException;
import by.itacademy.model.message.UnlockRequestMessage;
import by.itacademy.persistance.jdbc.mapper.AbstractResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UnlockRequestMessageResultSetMapper extends AbstractResultSetMapper<UnlockRequestMessage> {

    @Override
    public UnlockRequestMessage processResultSet(ResultSet rs) throws DaoException {
        try {
            int id = rs.getInt("message_id");
            int userId = rs.getInt("user_id");
            String userName = rs.getString("user_name");
            String messageBody = rs.getString("message_body");

            return UnlockRequestMessage.builder()
                    .id(id)
                    .userId(userId)
                    .userName(userName)
                    .messageBody(messageBody)
                    .build();
        } catch (SQLException e) {
            throw new DaoException("Error mapping resultSet to UnlockRequestMessage Object");
        }
    }
}
