package by.itacademy.exception;

public class InputDataValidationException extends Exception {
    public InputDataValidationException(String message) {
        super(message);
    }

    public InputDataValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
