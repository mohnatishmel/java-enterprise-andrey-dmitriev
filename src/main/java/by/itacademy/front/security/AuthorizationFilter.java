package by.itacademy.front.security;

import by.itacademy.exception.security.authorization.AuthorizationException;
import by.itacademy.security.SecurityConfigurer;
import by.itacademy.security.service.SecurityService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;

@Log4j2

@WebFilter(value = "/*")
public class AuthorizationFilter implements Filter {

    private FilterConfig config;

    @Override
    public void init(FilterConfig filterConfig) {
        config = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        try {

            String url = ((HttpServletRequest) request).getRequestURI();
            String servletPath = ((HttpServletRequest) request).getServletPath();

            boolean r = SecurityService.getInstance().authorize(url);
            boolean r2 = servletPath.matches(SecurityConfigurer.LOGIN_PAGE);
            if (SecurityService.getInstance().authorize(url)
                    || servletPath.matches(SecurityConfigurer.LOGIN_PAGE)) {
                filterChain.doFilter(request, response);
                return;
            } else {
                forward(request, response, SecurityConfigurer.LOGIN_PAGE, "You are not authorized to see this content");
                return;
            }

        } catch (AuthorizationException e) {
            String message = "Security Filter Exception";
            log.debug(message, Arrays.toString(e.getStackTrace()));
            forward(request, response, "/","Page not found");
            return;
        }

    }

    @Override
    public void destroy() {

    }

    private void forward(ServletRequest request, ServletResponse response, String url, String message) throws ServletException, IOException {
        request.setAttribute("error", message);
        config.getServletContext()
                .getRequestDispatcher(url)
                .forward(request, response);
    }
}
