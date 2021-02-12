package by.itacademy.security.service.authentication.provider;

import by.itacademy.security.exception.web.authentication.AuthenticationException;
import by.itacademy.security.model.authentication.Authentication;

public interface AuthenticationProvider {

    Authentication authenticate(Authentication credentials) throws AuthenticationException;

    boolean supports(Authentication authentication);
}
