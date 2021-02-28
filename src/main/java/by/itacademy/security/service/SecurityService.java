package by.itacademy.security.service;


import by.itacademy.exception.security.authorization.AuthorizationException;
import by.itacademy.exception.security.authorization.UrlPatternNotFoundException;
import by.itacademy.model.security.user.GrantedAuthority;
import by.itacademy.model.security.user.UserDetails;
import by.itacademy.security.service.web.config.WebSecurityConfig;

import java.util.Arrays;
import java.util.List;

public class SecurityService {

    private static SecurityService instance;

    public boolean authorize(GrantedAuthority ... authoritiesNeeded) throws AuthorizationException {
        return authorize(Arrays.asList(authoritiesNeeded));
    }

    public boolean authorize(String url) throws AuthorizationException {
            List<GrantedAuthority> authoritiesNeeded = WebSecurityConfig.getInstance().findMatches(url);
            return authorize(authoritiesNeeded);
    }

    private boolean authorize(List<GrantedAuthority> authoritiesNeeded ) throws AuthorizationException {
        boolean result = false;
        UserDetails p = SecurityContext.getInstance().getPrincipal();
        if (SecurityContext.getInstance().getPrincipal() != null) {
            List<GrantedAuthority> grantedAuthorities =
                    (List<GrantedAuthority>) SecurityContext.getInstance().getPrincipal().getAuthorities();
            if (!grantedAuthorities.isEmpty() && !authoritiesNeeded.isEmpty()) {
                for (GrantedAuthority grantedAuthority : grantedAuthorities) {
                    for (GrantedAuthority authorityNeeded : authoritiesNeeded) {
                        if (grantedAuthority != null && grantedAuthority.equals(authorityNeeded)) {
                            result = true;
                        }
                    }
                }
            }
        }
        return result;
    }

    public static SecurityService getInstance() {
        if (instance == null) {
            instance = new SecurityService();
        }
        return instance;
    }
}
