package by.itacademy.security.model;

import by.itacademy.security.exception.authentication.UsernameNotFoundException;

public interface UserDetailService {

    UserDetails loadUserByUsername(String name) throws UsernameNotFoundException;
}
