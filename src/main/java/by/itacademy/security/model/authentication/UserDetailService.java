package by.itacademy.security.model.authentication;

import by.itacademy.security.model.user.UserDetails;

public interface UserDetailService {

    UserDetails getByName(String name);
}
