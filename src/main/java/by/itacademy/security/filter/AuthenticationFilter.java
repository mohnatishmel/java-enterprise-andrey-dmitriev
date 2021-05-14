package by.itacademy.security.filter;

import by.itacademy.security.model.user.UserDetails;
import by.itacademy.security.service.SecurityContext;
import by.itacademy.security.service.web.config.WebSecurityConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RequiredArgsConstructor

@Component
@Order(1)
public class AuthenticationFilter implements Filter {

    private final SecurityContext securityContext;
    private final WebSecurityConfig webSecurityConfig;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpSession session = ((HttpServletRequest) request).getSession();
        String url = ((HttpServletRequest) request).getRequestURI();

        UserDetails principle = (UserDetails) session.getAttribute("principle");
        if (principle != null) {
            securityContext.setPrincipal(principle);
            chain.doFilter(request, response);
            return;
        }
        if (!url.matches(webSecurityConfig.getStaticResourcesPath() + ".*")
                && !url.matches(webSecurityConfig.getLoginPath() + ".*")
                && !url.matches(webSecurityConfig.getRegisterPath() + ".*")) {
            request.getServletContext()
                    .getRequestDispatcher(webSecurityConfig.getLoginPath())
                    .forward(request, response);
            return;
        }
        chain.doFilter(request, response);
    }
}
