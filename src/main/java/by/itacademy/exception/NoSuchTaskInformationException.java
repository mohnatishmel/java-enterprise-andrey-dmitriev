package by.itacademy.exception;

public class NoSuchTaskInformationException extends Exception {
    public NoSuchTaskInformationException() {
    }

    public NoSuchTaskInformationException(String message) {
        super(message);
    }

    public NoSuchTaskInformationException(String message, Throwable cause) {
        super(message, cause);
    }
}
