package by.itacademy.security.filter;

import by.itacademy.security.model.user.UserDetails;
import by.itacademy.security.service.SecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
@Order(1)
public class AuthenticationFilter implements Filter {

    private SecurityContext securityContext;

    @Autowired
    public void setSecurityContext(SecurityContext securityContext) {
        this.securityContext = securityContext;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        HttpSession session = ((HttpServletRequest) request).getSession();

            UserDetails principle = (UserDetails) session.getAttribute("principle");
        if (principle != null) {
            securityContext.setPrincipal(principle);
        }
//        else {
//            try {
//                request.getServletContext()
//                        .getRequestDispatcher("/")
//                        .forward(request, response);
//            } catch (ServletException e) {
//                e.printStackTrace();
//            }
//            return;
//        }
        chain.doFilter(request, response);
    }
}
