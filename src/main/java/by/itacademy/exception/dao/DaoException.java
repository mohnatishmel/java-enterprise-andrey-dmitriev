package by.itacademy.exception.dao;

import by.itacademy.exception.ApplicationBasedException;

public class DaoException extends ApplicationBasedException {

    public DaoException(String message) {
        super(message);
    }


    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }
}
