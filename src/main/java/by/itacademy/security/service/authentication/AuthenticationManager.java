package by.itacademy.security.service.authentication;

import by.itacademy.security.exception.web.authentication.AuthenticationException;
import by.itacademy.security.model.authentication.Authentication;
import by.itacademy.security.service.authentication.provider.AuthenticationProvider;


import java.util.ArrayList;
import java.util.List;

public class AuthenticationManager {

    private static AuthenticationManager instance;

    private List<AuthenticationProvider> authenticationProviders;

    {
        authenticationProviders = new ArrayList<>();
    }

    public boolean add(AuthenticationProvider authenticationProvider) {
        return authenticationProviders.add(authenticationProvider);
    }

    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Authentication principal = null;
        for (AuthenticationProvider authenticationProvider : authenticationProviders) {
            if (authenticationProvider.supports(authentication)) {
                principal = authenticationProvider.authenticate(authentication);
            }
        }
        if (principal != null) {
            return principal;
        } else {
            throw new AuthenticationException("No such authentication provider");
        }
    }

    public static AuthenticationManager getInstance() {
        if (instance == null) {
            instance = new AuthenticationManager();
        }
        return instance;
    }
}
