package by.itacademy.security.service;

import by.itacademy.security.model.user.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class SecurityContext {

    private static ThreadLocal<UserDetails> CURRENT_PRINCIPAL = new ThreadLocal<>();

    public UserDetails getPrincipal() {
        return CURRENT_PRINCIPAL.get();
    }

    public void setPrincipal(UserDetails principal) {
        CURRENT_PRINCIPAL.set(principal);
    }
}
