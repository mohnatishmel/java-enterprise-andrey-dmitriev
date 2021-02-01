package by.itacademy.dal;

import by.itacademy.model.user.User;

public interface UserDao extends CrudDao<User> {

    User fondUserByName(String name);

}
