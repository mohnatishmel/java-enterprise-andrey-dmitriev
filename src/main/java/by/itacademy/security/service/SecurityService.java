package by.itacademy.security.service;


import by.itacademy.exception.security.authorization.AuthorizationException;
import by.itacademy.security.model.user.GrantedAuthority;
import by.itacademy.security.model.user.UserDetails;
import by.itacademy.security.service.web.config.WebSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class SecurityService {

    private SecurityContext securityContext;

    @Autowired
    public SecurityService(SecurityContext securityContext) {
        this.securityContext = securityContext;
    }

    public boolean authorizeForCurrentUser(int id) {
        boolean result = false;
        int principalId = securityContext.getPrincipal().getId();

        if (principalId == id) {
            result = true;
        }
        return result;
    }

    public boolean authorize(GrantedAuthority ... authoritiesNeeded) throws AuthorizationException {
        return authorize(Arrays.asList(authoritiesNeeded));
    }

    public boolean authorize(String url) throws AuthorizationException {
            List<GrantedAuthority> authoritiesNeeded = WebSecurityConfig.getInstance().findMatches(url);
            return authorize(authoritiesNeeded);
    }

    private boolean authorize(List<GrantedAuthority> authoritiesNeeded ) throws AuthorizationException {
        boolean result = false;
        UserDetails p = securityContext.getPrincipal();
        if (securityContext.getPrincipal() != null) {
            List<GrantedAuthority> grantedAuthorities =
                    (List<GrantedAuthority>) securityContext.getPrincipal().getAuthorities();
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
}
