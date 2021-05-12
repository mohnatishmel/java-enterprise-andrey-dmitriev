package by.itacademy.config;

import by.itacademy.security.model.user.Roles;
import by.itacademy.security.service.web.config.WebSecurityConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@RequiredArgsConstructor

@Component
public class WebSecurityConfigurer {

    private final WebSecurityConfig webSecurityConfig;

    @PostConstruct
    public void init() {
        webSecurityConfig.setLoginPageLocation("/login");
        webSecurityConfig.setStaticResourcesLocation("/static");

        webSecurityConfig.add("/", Roles.GUEST_ROLE)
                .add("/static.*", Roles.GUEST_ROLE)
                .add("/login", Roles.GUEST_ROLE)
                .add("/register", Roles.GUEST_ROLE)
                .add("/logout", Roles.USER_ROLE, Roles.ADMIN_ROLE)
                .add("/principal", Roles.USER_ROLE, Roles.ADMIN_ROLE)
                .add("/main", Roles.USER_ROLE, Roles.ADMIN_ROLE)
                .add("/rest/tasks.*", Roles.USER_ROLE, Roles.ADMIN_ROLE)
                .add("/rest/users.*", Roles.ADMIN_ROLE)
                .add("/rest/tasks/.*/files", Roles.USER_ROLE, Roles.ADMIN_ROLE)
                .add("/rest/unlock/requests/messages", Roles.USER_ROLE, Roles.ADMIN_ROLE);
    }
}
