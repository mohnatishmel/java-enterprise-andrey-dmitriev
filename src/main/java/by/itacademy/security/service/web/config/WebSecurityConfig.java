package by.itacademy.security.service.web.config;

import by.itacademy.model.user.Role;
import by.itacademy.security.exception.web.UrlPatternNotFoundException;
import by.itacademy.security.exception.web.UrlHasNotBeenDefinedException;
import by.itacademy.security.model.GrantedAuthority;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class WebSecurityConfig {

    private static WebSecurityConfig instance;

    private static List<String> urls;
    private static List<List<GrantedAuthority>> authorities;

    @Setter
    private String loginUrl;
    @Setter
    private String notAuthorizedUrl;

    {
        urls = new ArrayList<>();
        authorities = new ArrayList<>();
    }

    public WebSecurityConfig add(String url, String ... roles) {
        urls.add(url);
        for (String role : roles) {
            authorities.add((List<GrantedAuthority>) new Role(role));
        }
        return this;
    }

    public List<GrantedAuthority> findMatches(String url) throws UrlPatternNotFoundException {
        for (int i = 0; i < urls.size(); i++) {
            String urlPattern = urls.get(i);
            if (url.matches(urlPattern)) {
                return authorities.get(i);
            }
        }
        throw new UrlPatternNotFoundException("No matches to url '" + url + "'");
    }

    public String getLoginUrl() throws UrlHasNotBeenDefinedException {
        if (loginUrl == null) {
            throw new UrlHasNotBeenDefinedException("login url has no been defined in WebSecurityConfig");
        }
        return loginUrl;
    }

    public String getNotAuthorizedUrl() throws UrlHasNotBeenDefinedException {
        if (notAuthorizedUrl == null) {
            throw new UrlHasNotBeenDefinedException("'Not authorized' url has no been defined in WebSecurityConfig");
        }
        return notAuthorizedUrl;
    }

    public static WebSecurityConfig getInstance() {
        if (instance == null) {
            instance = new WebSecurityConfig();
        }
        return instance;
    }
}
