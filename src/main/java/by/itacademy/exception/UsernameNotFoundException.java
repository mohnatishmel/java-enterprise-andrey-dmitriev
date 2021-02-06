package by.itacademy.exception;

public class UsernameNotFoundException extends Exception {

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
