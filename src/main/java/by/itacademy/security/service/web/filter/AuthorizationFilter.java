package by.itacademy.security.service.web.filter;

import by.itacademy.security.exception.web.UrlHasNotBeenDefinedException;
import by.itacademy.security.exception.web.UrlPatternNotFoundException;
import by.itacademy.security.service.SecurityContext;
import by.itacademy.security.service.web.WebSecurityService;
import by.itacademy.security.service.web.config.WebSecurityConfig;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Log4j2

@WebFilter("/*")
public class AuthorizationFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        try {

            if (SecurityContext.getInstance().getPrincipal() == null) {
                ((HttpServletRequest) servletRequest)
                        .getRequestDispatcher(WebSecurityConfig.getInstance()
                                .getLoginUrl())
                        .forward(servletRequest, servletResponse);
                return;
            }

            String url = ((HttpServletRequest) servletRequest).getRequestURL().toString();
            if (WebSecurityService.getInstance().authorize(url)) {
                filterChain.doFilter(servletRequest, servletResponse);

            } else {
                ((HttpServletResponse) servletResponse).sendRedirect(WebSecurityConfig.getInstance().getNotAuthorizedUrl());
            }
            filterChain.doFilter(servletRequest, servletResponse);

        } catch (UrlPatternNotFoundException | UrlHasNotBeenDefinedException e) {
            String message = "Security Filter Exception";
            log.debug(message, Arrays.toString(e.getStackTrace()));
            throw new ServletException(message, e);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
