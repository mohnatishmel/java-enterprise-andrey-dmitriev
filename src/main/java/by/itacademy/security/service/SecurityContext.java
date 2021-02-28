package by.itacademy.security.service;

import by.itacademy.model.security.user.UserDetails;

public class SecurityContext {

    private static ThreadLocal<UserDetails> CURRENT_PRINCIPAL = new ThreadLocal<>();
    private static SecurityContext instance;

    public UserDetails getPrincipal() {
        return CURRENT_PRINCIPAL.get();
    }

    public void setPrincipal(UserDetails principal) {
        CURRENT_PRINCIPAL.set(principal);
    }

    public static SecurityContext getInstance() {
        if (instance == null) {
            instance = new SecurityContext();
        }
        return instance;
    }

    public boolean isLogedIn() {
        return CURRENT_PRINCIPAL.get() == null;
    }
}
