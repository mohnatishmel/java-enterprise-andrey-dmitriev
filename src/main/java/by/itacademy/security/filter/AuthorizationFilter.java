package by.itacademy.security.filter;

import by.itacademy.exception.security.authorization.AuthorizationException;
import by.itacademy.exception.security.authorization.UrlPatternNotFoundException;
import by.itacademy.security.service.SecurityService;
import by.itacademy.security.service.web.config.WebSecurityConfig;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Log4j2

@Component
@Order(2)
public class AuthorizationFilter implements Filter {

    private SecurityService securityService;
    private WebSecurityConfig webSecurityConfig;

    @Autowired
    public void setSecurityContext(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Autowired
    public void setWebSecurityConfig(WebSecurityConfig webSecurityConfig) {
        this.webSecurityConfig = webSecurityConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String url = ((HttpServletRequest) request).getRequestURI();

        try {
            if (securityService.authorize(url)) {
                filterChain.doFilter(request, response);
                return;
            }
        } catch (UrlPatternNotFoundException e) {
            log.debug(e);
            request.setAttribute("errorMessage", "Oops,<br>Something went wrong! :(" );
            request.getServletContext()
                    .getRequestDispatcher(webSecurityConfig.getErrorPath())
                    .forward(request, response);

        } catch (AuthorizationException e) {
            log.debug(e);
            request.setAttribute("errorMessage", e.getMessage() + " :(");
            request.getServletContext()
                    .getRequestDispatcher(webSecurityConfig.getErrorPath())
                    .forward(request, response);
        }
        return;
    }
}
