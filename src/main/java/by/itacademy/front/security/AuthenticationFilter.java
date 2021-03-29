package by.itacademy.front.security;

import by.itacademy.model.security.user.UserDetails;
import by.itacademy.security.SecurityConfigurer;
import by.itacademy.security.service.SecurityContext;
import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@Log4j2

@WebFilter(value = "/*")
public class AuthenticationFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        String servletPath = ((HttpServletRequest) request).getServletPath();

        HttpSession session = ((HttpServletRequest) request).getSession();

            UserDetails principle = (UserDetails) session.getAttribute("principle");
        if (principle != null) {
            SecurityContext.getInstance().setPrincipal(principle);
        } else {
            request.getServletContext()
                    .getRequestDispatcher("/")
                    .forward(request, response);
            return;
        }

        chain.doFilter(request, response);
    }
}
