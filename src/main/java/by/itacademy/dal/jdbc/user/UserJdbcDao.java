package by.itacademy.dal.jdbc.user;

import by.itacademy.dal.UserDao;


public class UserJdbcDao implements UserDao {

    private final String getByIdSql;
    private final String getAllSql;
    private final String updateSql;
    private final String createSql;
    private final String deleteSql;

    {
        getByIdSql = "SELECT 'user_id', 'credentials_id', 'personal_information_id', `profile_enable`  FROM 'users', WHERE 'user_id' = ?;";
        getAllSql = "SELECT 'user_id', 'credentials_id', 'personal_information_id', `profile_enable`   FROM 'users';";
        updateSql = "UPDATE  users SET `profile_enable` , WHERE 'user_id' = ?;";
        createSql = "INSERT INTO users(SELECT 'credentials_id', 'personal_information_id', `profile_enable`) VALUES(?,?,?);";
        deleteSql = "DELETE FROM users, WHERE 'user_id' = ?;";
    }


}
