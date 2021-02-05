package by.itacademy.dal;


import by.itacademy.model.user.User;
import by.itacademy.security.UserDetailService;

public interface UserDao extends CrudDao<User>, UserDetailService {


}
