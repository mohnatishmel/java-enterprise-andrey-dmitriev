package by.itacademy.security.web.config;

import by.itacademy.exception.UrlPatternNotFoundException;
import by.itacademy.security.GrantedAuthority;

import java.util.Arrays;
import java.util.List;

public class WebSecurityConfig {

    private static List<String> urls;
    private static List<List<GrantedAuthority>> authorities;

    public WebSecurityConfig add(String url, GrantedAuthority... auth) {
        urls.add(url);
        authorities.add(Arrays.asList(auth));
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
}
