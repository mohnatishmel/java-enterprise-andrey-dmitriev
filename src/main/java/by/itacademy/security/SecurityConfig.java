package by.itacademy.security;

import by.itacademy.security.model.UserDetailService;
import lombok.Getter;
import lombok.Setter;

public class SecurityConfig {

    @Getter
    @Setter
    private UserDetailService userDetailService;
}
