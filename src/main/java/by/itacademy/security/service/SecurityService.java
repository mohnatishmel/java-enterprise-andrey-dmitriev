package by.itacademy.security.service;

import by.itacademy.exception.security.authorization.AuthorizationException;
import by.itacademy.security.model.user.GrantedAuthority;
import by.itacademy.security.model.user.Roles;
import by.itacademy.security.model.user.UserDetails;
import by.itacademy.security.service.web.config.WebSecurityConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor

@Service
public class SecurityService {

    private final SecurityContext securityContext;
    private final WebSecurityConfig webSecurityConfig;

    public boolean authorizeForCurrentUser(int id) {
        boolean result = false;
        int principalId = securityContext.getPrincipal().getId();

        if (principalId == id) {
            result = true;
        }
        return result;
    }

    public boolean authorize(GrantedAuthority... authoritiesNeeded) throws AuthorizationException {
        return authorize(Arrays.asList(authoritiesNeeded));
    }

    public boolean authorize(String url) throws AuthorizationException {
        List<GrantedAuthority> authoritiesNeeded = webSecurityConfig.findMatches(url);
        if (authoritiesNeeded.stream().filter(role -> role.getAuthority().equals(Roles.GUEST_ROLE)).findAny().isPresent()) {
            return true;
        }
        return authorize(authoritiesNeeded);
    }

    private boolean authorize(List<GrantedAuthority> authoritiesNeeded) throws AuthorizationException {
        UserDetails principle = securityContext.getPrincipal();
        if (principle != null) {
            List<GrantedAuthority> grantedAuthorities = (List<GrantedAuthority>) principle.getAuthorities();

            for (GrantedAuthority grantedAuthority : grantedAuthorities) {
                for (GrantedAuthority authorityNeeded : authoritiesNeeded) {
                    if (grantedAuthority != null && grantedAuthority.equals(authorityNeeded)
                            || grantedAuthority.getAuthority().equals(Roles.GUEST_ROLE)) {
                        return true;
                    }
                }
            }
        }
        throw new AuthorizationException("You are not authorized for this action");
    }
}
