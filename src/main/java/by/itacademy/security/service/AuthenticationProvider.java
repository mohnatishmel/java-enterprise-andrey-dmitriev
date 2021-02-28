package by.itacademy.security.service;

import by.itacademy.persistance.UserDao;
import by.itacademy.persistance.jdbc.dao.user.UserJdbcDao;
import by.itacademy.exception.security.authentication.AuthenticationException;
import by.itacademy.exception.security.authentication.BadCredentialsException;
import by.itacademy.model.security.user.UserDetails;
import by.itacademy.model.security.authentication.AuthenticationToken;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2


@NoArgsConstructor
public class AuthenticationProvider {

    private static AuthenticationProvider instance;
    private static UserDao userDetailService;

    {
        userDetailService = UserJdbcDao.getInstance();
    }

    public UserDetails authenticate(AuthenticationToken authenticationToken) throws AuthenticationException {
        UserDetails user = null;

        String login = authenticationToken.getLogin();
        String password = authenticationToken.getPassword();

        if (login != null) {
            user = userDetailService.getByName(login);
            if (!password.equals(user.getPassword())) {
                log.debug("bad credentials");
                throw new BadCredentialsException();
            }
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
