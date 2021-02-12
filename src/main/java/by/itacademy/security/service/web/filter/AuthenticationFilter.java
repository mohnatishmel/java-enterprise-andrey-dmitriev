package by.itacademy.security.service.web.filter;

import javax.servlet.Filter;
import javax.servlet.ServletRequestEvent;

import javax.servlet.http.HttpServletRequest;

public class AuthenticationFilter implements Filter {


        HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
        String urlPattern = UrlPatternUtils.getUrlPattern(request);
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        User user = (User) session.getAttribute("loged_user");
        SecurityContextHolder.setLoggedUser(user);
    }
}
