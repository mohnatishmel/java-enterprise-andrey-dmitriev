package by.itacademy.security.service;

import by.itacademy.security.exception.authentication.AuthenticationException;
import by.itacademy.security.exception.authentication.BadCredentialsException;
import by.itacademy.security.exception.authentication.UsernameNotFoundException;
import by.itacademy.security.model.UserDetailService;
import by.itacademy.security.model.UserDetails;
import by.itacademy.security.model.authentication.AuthenticationToken;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2

@NoArgsConstructor
public class AuthenticationProvider {

    private static AuthenticationProvider instance;
    private UserDetailService userDetailService;

    public AuthenticationProvider(UserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }


    public UserDetails authenticate(AuthenticationToken authenticationToken) throws AuthenticationException {
        UserDetails user = null;
        try {
            String login = authenticationToken.getLogin();
            String password = authenticationToken.getPassword();

            if (login != null) {
                user = userDetailService.loadUserByUsername(login);
                if (password != null && password.equals(user.getPassword())) {

                } else {
                    log.debug("bad credentials");
                    throw new BadCredentialsException();
                }
            }
        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static AuthenticationProvider getInstance() {
        if (instance == null) {
            instance = new AuthenticationProvider();
        }
        return instance;
    }
}
