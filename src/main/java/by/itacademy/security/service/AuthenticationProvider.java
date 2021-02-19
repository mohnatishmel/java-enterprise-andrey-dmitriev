package by.itacademy.security.service;

import by.itacademy.dal.UserDao;
import by.itacademy.dal.jdbc.dao.user.UserJdbcDao;
import by.itacademy.exception.dao.DaoException;
import by.itacademy.exception.security.authentication.AuthenticationException;
import by.itacademy.exception.security.authentication.BadCredentialsException;
import by.itacademy.exception.security.authentication.UsernameNotFoundException;
import by.itacademy.model.security.user.UserDetails;
import by.itacademy.model.security.authentication.AuthenticationToken;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.Arrays;

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
        try {
            String login = authenticationToken.getLogin();
            String password = authenticationToken.getPassword();

            if (login != null) {
                user = userDetailService.getByName(login);
                if (! password.equals(user.getPassword())) {
                    log.debug("bad credentials");
                    throw new BadCredentialsException();
                }
            }
        } catch (DaoException e) {
            String message = String.format("User with login %s not found", authenticationToken.getLogin());
//            log.debug(message, Arrays.toString(e.getStackTrace()));
            throw new UsernameNotFoundException(message, e);
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
