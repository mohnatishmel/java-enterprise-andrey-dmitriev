package by.itacademy.security.service;

import by.itacademy.exception.security.authentication.AuthenticationException;
import by.itacademy.exception.security.authentication.BadCredentialsException;
import by.itacademy.security.model.authentication.UserDetailService;
import by.itacademy.security.model.user.UserDetails;
import by.itacademy.security.model.authentication.AuthenticationToken;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;


@Log4j2

@Service
public class AuthenticationProvider {

    private UserDetailService userDetailService;

    @Autowired
    public AuthenticationProvider(UserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    public UserDetails authenticate(AuthenticationToken authenticationToken) throws AuthenticationException {
        UserDetails user = null;

        String login = authenticationToken.getLogin();
        String password = authenticationToken.getPassword();

        if (login != null) {
            try {
                user = userDetailService.getByName(login);
                if (!password.equals(user.getPassword())) {
                    log.debug("bad credentials");
                    throw new BadCredentialsException();
                }
            } catch (DataAccessException e) {

            }
        }

        return user;
    }
}
