package by.itacademy.security.service.web.config;

import by.itacademy.entities.user.Role;
import by.itacademy.exception.security.authorization.UrlPatternNotFoundException;
import by.itacademy.security.model.user.GrantedAuthority;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Log4j2

@Component
public class WebSecurityConfig {

    @Getter
    @Setter
    private String staticResourcesPath = "/static/";
    @Getter
    @Setter
    private String loginPath = "/login/";
    @Getter
    @Setter
    private String registerPath = "/register/";
    @Getter
    @Setter
    private String errorPath = "/error/";


    private List<String> urls;
    private List<List<GrantedAuthority>> authorities;

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
}
