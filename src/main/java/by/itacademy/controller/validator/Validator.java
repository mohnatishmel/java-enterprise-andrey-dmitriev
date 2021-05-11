package by.itacademy.controller.validator;

public interface Validator<T> {
    boolean validate(T t);
}
