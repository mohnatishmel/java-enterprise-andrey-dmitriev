package by.itacademy.config;

import by.itacademy.constant.Constant;
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
        webSecurityConfig.setLoginPath(Constant.LOGIN_PATH);
        webSecurityConfig.setRegisterPath(Constant.REGISTRATION_PATH);
        webSecurityConfig.setStaticResourcesPath(Constant.STATIC_RESOURCES_PATH);
        webSecurityConfig.setErrorPath(Constant.ERROR_PATH);

        webSecurityConfig.add("/", Roles.GUEST)
                .add(Constant.ERROR_PATH + ".*", Roles.GUEST)
                .add(Constant.STATIC_RESOURCES_PATH + ".*", Roles.GUEST)
                .add(Constant.LOGIN_PATH + ".*", Roles.GUEST)
                .add(Constant.REGISTRATION_PATH + ".*", Roles.GUEST)
                .add("/logout", Roles.USER, Roles.ADMIN)
                .add("/principal", Roles.USER, Roles.ADMIN)
                .add("/main", Roles.USER, Roles.ADMIN)
                .add("/rest/tasks.*", Roles.USER, Roles.ADMIN)
                .add("/rest/users.*", Roles.ADMIN)
                .add("/rest/tasks/.*/files", Roles.USER, Roles.ADMIN)
                .add("/rest/unlock/requests/messages", Roles.USER, Roles.ADMIN);
    }
}
