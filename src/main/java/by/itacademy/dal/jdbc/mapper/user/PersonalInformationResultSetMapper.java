package by.itacademy.dal.jdbc.mapper.user;

import by.itacademy.dal.jdbc.mapper.AbstractResultSetMapper;
import by.itacademy.exception.dao.DaoException;
import by.itacademy.model.user.PersonalInformation;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonalInformationResultSetMapper extends AbstractResultSetMapper<PersonalInformation> {

    @Override
    public PersonalInformation processResultSet(ResultSet rs) throws DaoException {
        try {
            int id = rs.getInt("personal_information_id");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");

            return new PersonalInformation(id, firstName, lastName);
        } catch (SQLException e) {
            throw new DaoException("Error mapping resultSet to personal_information Object");
        }
    }
}
