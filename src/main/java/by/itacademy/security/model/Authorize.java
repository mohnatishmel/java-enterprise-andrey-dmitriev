package by.itacademy.security.model;

import by.itacademy.security.exception.web.UrlPatternNotFoundException;

public interface Authorize<T> {

    boolean authorize(T t) throws UrlPatternNotFoundException;
}
