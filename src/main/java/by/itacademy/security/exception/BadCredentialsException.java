package by.itacademy.security.exception;

public class BadCredentialsException extends Exception {
    public BadCredentialsException() {
    }

    public BadCredentialsException(String message) {
        super(message);
    }
}
