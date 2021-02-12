package by.itacademy.security.exception.web.authentication;

public class UsernameNotFoundException extends AuthenticationException {

    public UsernameNotFoundException(Throwable cause) {
        super(cause);
    }

    public UsernameNotFoundException(String message) {
        super(message);
    }

    public UsernameNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
