package by.itacademy.security;

import by.itacademy.exception.UsernameNotFoundException;

public interface UserDetailService {

    UserDetails loadUserByUsername(String name) throws UsernameNotFoundException;
}
