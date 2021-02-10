package by.itacademy.security.web;

import by.itacademy.exception.UrlPatternNotFoundException;
import by.itacademy.security.Authenticate;
import by.itacademy.security.GrantedAuthority;
import by.itacademy.security.SecurityContext;
import by.itacademy.security.web.config.WebSecurityConfig;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor

public class WebSecurityService implements Authenticate<String> {

    private SecurityContext securityContext;
    private WebSecurityConfig config;

    @Override
    public boolean authenticate(String url) throws UrlPatternNotFoundException {
        List<GrantedAuthority> grantedAuthorities = (List<GrantedAuthority>) securityContext.getPrincipal().getAuthorities();
        List<GrantedAuthority> authoritiesNeeded = config.findMatches(url);

        if (!grantedAuthorities.isEmpty() && !authoritiesNeeded.isEmpty()) {

            for (GrantedAuthority grantedAuthority : grantedAuthorities) {
                for (GrantedAuthority authorityNeeded : authoritiesNeeded) {
                    if (grantedAuthority != null && grantedAuthority.equals(authorityNeeded)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
