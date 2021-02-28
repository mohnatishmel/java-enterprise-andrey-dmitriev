package by.itacademy.security;

import by.itacademy.security.service.web.config.WebSecurityConfig;

public class SecurityConfigurer {

    public static final String LOGIN_PAGE = "/WEB-INF/jsp/login.jsp";

    public static void init() {
        WebSecurityConfig.getInstance().add("/pages/main.jsp", "USER_ROLE", "ADMIN_ROLE")
                .add("/pages/admin.jsp", "ADMIN_ROLE")
                .add("/", "USER_ROLE", "ADMIN_ROLE");
    }

}
