package by.itacademy.security.service.web;

import by.itacademy.security.exception.web.UrlPatternNotFoundException;
import by.itacademy.security.service.Authenticate;
import by.itacademy.security.model.GrantedAuthority;
import by.itacademy.security.service.SecurityContext;
import by.itacademy.security.service.web.config.WebSecurityConfig;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor

public class WebSecurityService implements Authenticate<String> {

    private static WebSecurityService instance;

    @Override
    public boolean authorize(String url) throws UrlPatternNotFoundException {
        List<GrantedAuthority> grantedAuthorities =
                (List<GrantedAuthority>) SecurityContext.getInstance().getPrincipal().getAuthorities();
        List<GrantedAuthority> authoritiesNeeded = WebSecurityConfig.getInstance().findMatches(url);

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

    public static WebSecurityService getInstance() {
        if (instance == null) {
            instance = new WebSecurityService();
        }
        return instance;
    }
}
