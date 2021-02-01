package by.itacademy.dal.jdbc.user;

import by.itacademy.dal.UserDao;
import by.itacademy.model.user.User;

import java.util.List;

public class UserJdbcDao implements UserDao {

    private final String getByIdSql;
    private final String getAllSql;
    private final String updateSql;
    private final String createSql;
    private final String deleteSql;

    {
        getByIdSql = "SELECT 'id', 'credentials_id', 'personal_information_id'  FROM task_information, WHERE id = ?;";
        getAllSql = "SELECT 'id', 'description', 'filepath'  FROM task_information;";
        updateSql = "UPDATE  task_information SET 'description' = ?, 'filepath' = ?, WHERE id = ?;";
        createSql = "INSERT INTO task_information('description', 'filepath') VALUES(?,?);";
        deleteSql = "DELETE FROM task_information, WHERE id = ?;";
    }

    @Override
    public User getById(long id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User create(User user) {
        return null;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public User fondUserByName(String name) {
        return null;
    }
}
