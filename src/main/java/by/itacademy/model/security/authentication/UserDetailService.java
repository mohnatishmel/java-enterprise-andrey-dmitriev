package by.itacademy.model.security.authentication;

import by.itacademy.exception.security.authentication.UsernameNotFoundException;
import by.itacademy.model.security.user.UserDetails;

public interface UserDetailService {

    UserDetails getByName(String name) throws UsernameNotFoundException;
}
