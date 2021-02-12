package by.itacademy.security.model;

import by.itacademy.security.exception.web.authentication.UsernameNotFoundException;

public interface UserDetailService {

    UserDetails loadUserByUsername(String name) throws UsernameNotFoundException;
}
