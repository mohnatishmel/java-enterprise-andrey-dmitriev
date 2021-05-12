package by.itacademy.security.filter;

import by.itacademy.exception.security.authorization.AuthorizationException;
import by.itacademy.security.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@Order(2)
public class AuthorizationFilter implements Filter {

    private SecurityService securityService;

    @Autowired
    public void setSecurityContext(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String url = ((HttpServletRequest) request).getRequestURI();

        try {
            if (securityService.authorize(url)) {
                filterChain.doFilter(request, response);
                return;
            }
        } catch (AuthorizationException e) {
            e.printStackTrace();
        }
        return;
    }
}
