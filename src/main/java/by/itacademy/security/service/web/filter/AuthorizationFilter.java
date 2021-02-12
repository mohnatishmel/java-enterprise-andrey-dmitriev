package by.itacademy.security.service.web.filter;

import by.itacademy.security.service.SecurityContext;
import by.itacademy.security.service.web.WebSecurityService;
import by.itacademy.security.service.web.config.WebSecurityConfig;
import lombok.SneakyThrows;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/"})
public class AuthorizationFilter implements Filter {

    @SneakyThrows
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        if(SecurityContext.getInstance().getPrincipal() == null) {
            ((HttpServletResponse) servletResponse).sendRedirect(WebSecurityConfig.getInstance().getLoginUrl());
        }

        String url =  ((HttpServletRequest)servletRequest).getRequestURL().toString();
        if (WebSecurityService.getInstance().authorize(url)) {
            filterChain.doFilter(servletRequest, servletResponse);

        } else {
            ((HttpServletResponse) servletResponse).sendRedirect(WebSecurityConfig.getInstance().getNotAuthorizedUrl());
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
