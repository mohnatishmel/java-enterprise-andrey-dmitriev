package by.itacademy.security.service;

import by.itacademy.security.exception.web.UrlPatternNotFoundException;

public interface Authenticate<T> {

    boolean authorize(T t) throws UrlPatternNotFoundException;
}
