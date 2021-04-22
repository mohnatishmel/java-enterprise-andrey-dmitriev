package by.itacademy.security.filter;

import by.itacademy.security.model.user.UserDetails;
import by.itacademy.security.service.SecurityContext;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(value = "/*")
public class AuthenticationFilter implements Filter {
    public void init(FilterConfig config) {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        HttpSession session = ((HttpServletRequest) request).getSession();

            UserDetails principle = (UserDetails) session.getAttribute("principle");
        if (principle != null) {
            SecurityContext.getInstance().setPrincipal(principle);
        } else {
            try {
                request.getServletContext()
                        .getRequestDispatcher("/")
                        .forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            }
            return;
        }
        chain.doFilter(request, response);
    }
}
