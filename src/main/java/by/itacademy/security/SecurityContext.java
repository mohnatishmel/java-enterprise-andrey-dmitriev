package by.itacademy.security;

public class SecurityContext {

    ThreadLocal<UserDetails> currentPrincipal;

    public UserDetails getPrincipal() {
        return currentPrincipal.get();
    }

    public void setPrincipal(UserDetails principal) {
        currentPrincipal.set(principal);
    }
}
