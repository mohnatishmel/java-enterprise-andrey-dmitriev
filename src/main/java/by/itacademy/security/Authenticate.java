package by.itacademy.security;

import by.itacademy.exception.UrlPatternNotFoundException;

public interface Authenticate<T> {

    boolean authenticate(T t) throws UrlPatternNotFoundException;
}
