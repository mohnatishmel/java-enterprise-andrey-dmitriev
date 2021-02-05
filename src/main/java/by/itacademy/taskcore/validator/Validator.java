package by.itacademy.taskcore.validator;

@FunctionalInterface
public interface Validator <T> {

    boolean validate(T t);

}
