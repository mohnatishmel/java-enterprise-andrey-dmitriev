package by.itacademy.security.exception;

public class NoSuchAuthenticationMethodException extends Exception {

    public NoSuchAuthenticationMethodException() {
    }

    public NoSuchAuthenticationMethodException(String message) {
        super(message);
    }
}
