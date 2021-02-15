package by.itacademy.security.controller.filter;

import by.itacademy.security.service.SecurityContext;
import by.itacademy.security.service.web.config.WebSecurityConfig;
import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;

@Log4j2

@WebFilter("/*")
public class AuthenticationFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
            String servletPath =((HttpServletRequest) request).getServletPath();
            if (SecurityContext.getInstance().getPrincipal() == null && servletPath.matches("/login")) {
                ((HttpServletRequest) request)
                        .getRequestDispatcher("/pages/login.jsp")
                        .forward(request, response);
                return;
            }

            chain.doFilter(request, response);
    }
}
