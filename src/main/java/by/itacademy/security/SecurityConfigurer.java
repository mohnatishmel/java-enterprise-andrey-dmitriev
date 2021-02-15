package by.itacademy.security;

import by.itacademy.security.service.web.config.WebSecurityConfig;

public class SecurityConfigurer {

    public static void init() {
        WebSecurityConfig.getInstance().add("/pages/user.jsp", "USER_ROLE", "ADMIN_ROLE")
                .add("/pages/admin.jsp", "ADMIN_ROLE");
    }

}
