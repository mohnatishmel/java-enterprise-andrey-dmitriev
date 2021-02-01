package by.itacademy.security;

public interface SecurityContext {

    Authentication getAuthentication();

    void setAuthentication(Authentication authentication);
}
