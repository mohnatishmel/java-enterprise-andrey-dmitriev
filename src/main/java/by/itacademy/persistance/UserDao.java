package by.itacademy.persistance;


import by.itacademy.exception.dao.DaoException;
import by.itacademy.model.security.authentication.UserDetailService;
import by.itacademy.model.user.User;

import java.util.List;

public interface UserDao extends CrudDao<User>, UserDetailService {

    List<User> getAll() throws DaoException;

}
