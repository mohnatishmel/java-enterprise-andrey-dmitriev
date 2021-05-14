package by.itacademy.exception;

public class ApplicationBasedException extends Exception {
    
    public ApplicationBasedException(String message) {
        super(message);
    }

    public ApplicationBasedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplicationBasedException(Throwable cause) {
        super(cause);
    }
}
