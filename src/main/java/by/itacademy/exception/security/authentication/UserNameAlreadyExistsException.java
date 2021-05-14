package by.itacademy.exception.security.authentication;

public class UserNameAlreadyExistsException extends AuthenticationException {

    public UserNameAlreadyExistsException(String message) {
        super(message);
    }

}
