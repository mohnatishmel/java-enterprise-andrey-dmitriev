package by.itacademy.security.model.authentication;

import by.itacademy.exception.security.authentication.UsernameNotFoundException;
import by.itacademy.security.model.user.UserDetails;

public interface UserDetailService {

    UserDetails getByName(String name) throws UsernameNotFoundException;
}
