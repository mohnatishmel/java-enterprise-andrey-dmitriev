package by.itacademy.dal;


import by.itacademy.exception.DaoException;
import by.itacademy.model.user.User;
import by.itacademy.security.model.UserDetailService;

import java.util.List;

public interface UserDao extends CrudDao<User>, UserDetailService{

    List<User> getAll() throws DaoException;

}
