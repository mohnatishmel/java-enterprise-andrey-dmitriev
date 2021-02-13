package by.itacademy.security.service.web.config;

import by.itacademy.model.user.Role;
import by.itacademy.security.exception.web.UrlPatternNotFoundException;
import by.itacademy.security.exception.web.UrlHasNotBeenDefinedException;
import by.itacademy.security.model.GrantedAuthority;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;

@Log4j2

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
        List<GrantedAuthority> roleList = new ArrayList<>();
        for (String role : roles) {
            roleList.add(new Role(role));
        }
        authorities.add(roleList);
        return this;
    }

    public List<GrantedAuthority> findMatches(String url) throws UrlPatternNotFoundException {
        for (int i = 0; i < urls.size(); i++) {
            String urlPattern = urls.get(i);
            if (url.matches(urlPattern)) {
                return authorities.get(i);
            }
        }
        String message = "No matches to url '" + url + "'";
        log.debug(message);
        throw new UrlPatternNotFoundException(message);
    }

    public String getLoginUrl() throws UrlHasNotBeenDefinedException {
        if (loginUrl == null) {
            String message = "login url has no been defined in WebSecurityConfig";
            log.debug(message);
            throw new UrlHasNotBeenDefinedException(message);
        }
        return loginUrl;
    }

    public String getNotAuthorizedUrl() throws UrlHasNotBeenDefinedException {
        if (notAuthorizedUrl == null) {
            String message = "'Not authorized' url has no been defined in WebSecurityConfig";
            log.debug(message);
            throw new UrlHasNotBeenDefinedException(message);
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
