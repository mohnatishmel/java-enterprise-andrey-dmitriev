package by.itacademy.security.service.authentication.provider;

import by.itacademy.security.exception.web.authentication.AuthenticationException;
import by.itacademy.security.exception.web.authentication.BadCredentialsException;
import by.itacademy.security.exception.web.authentication.UsernameNotFoundException;
import by.itacademy.security.model.UserDetailService;
import by.itacademy.security.model.UserDetails;
import by.itacademy.security.model.authentication.Authentication;
import by.itacademy.security.model.authentication.LoginPasswordAuthenticationToken;

public class LoginPasswordAuthenticationProvider implements AuthenticationProvider {

    UserDetailService userDetailService;

    public LoginPasswordAuthenticationProvider(UserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @Override
    public Authentication authenticate(Authentication credentials) throws AuthenticationException {
        LoginPasswordAuthenticationToken token = (LoginPasswordAuthenticationToken) credentials;
        try {
            String login = (String) credentials.getPrincipal();
            String password = (String) credentials.getCredentials();

            if (login != null) {
                UserDetails user = userDetailService.loadUserByUsername(login);
                if (password != null && password.equals(user.getPassword())) {
                    token.eraseCredentials();
                    token.setPrincipal(user);
                    token.setAuthenticated(true);
                } else {
                    throw new BadCredentialsException();
                }
            }
        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
        }
        return credentials;
    }

    @Override
    public boolean supports(Authentication authentication) {
        return authentication.getClass() == LoginPasswordAuthenticationToken.class;
    }
}
