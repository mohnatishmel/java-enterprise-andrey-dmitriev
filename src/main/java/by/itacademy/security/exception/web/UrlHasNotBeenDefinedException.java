package by.itacademy.security.exception.web;

public class UrlHasNotBeenDefinedException extends Exception {

    public UrlHasNotBeenDefinedException(String message) {
        super(message);
    }

    public UrlHasNotBeenDefinedException(String message, Throwable cause) {
        super(message, cause);
    }
}
