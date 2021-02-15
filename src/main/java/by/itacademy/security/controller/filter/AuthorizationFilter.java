package by.itacademy.security.controller.filter;

import by.itacademy.security.exception.authorization.AuthorizationException;
import by.itacademy.security.service.SecurityService;
import by.itacademy.security.service.web.config.WebSecurityConfig;
import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;

@Log4j2

@WebFilter("/*")
public class AuthorizationFilter implements Filter {

    private FilterConfig config;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        config = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        try {

            String url = ((HttpServletRequest) servletRequest).getRequestURI();
            if ( ! SecurityService.getInstance().authorize(url)) {
                config.getServletContext()
                        .getRequestDispatcher("/pages/accessDenied.jsp")
                        .forward(servletRequest,servletResponse);
                return;
            }

            filterChain.doFilter(servletRequest, servletResponse);

        } catch (AuthorizationException e) {
            String message = "Security Filter Exception";
            log.debug(message, Arrays.toString(e.getStackTrace()));
            throw new ServletException(message, e);
        }
    }


    @Override
    public void destroy() {

    }
}
