package by.itacademy.controller.exception.handler;

import by.itacademy.entities.front.Message;
import by.itacademy.exception.InputDataValidationException;
import by.itacademy.exception.security.authentication.AuthenticationException;
import by.itacademy.exception.security.authentication.BadCredentialsException;
import by.itacademy.exception.security.authentication.UserNameAlreadyExistsException;
import by.itacademy.exception.security.authentication.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {UserNotFoundException.class, BadCredentialsException.class})
    public ResponseEntity<Message> exceptionHandling(AuthenticationException e) {
        return new ResponseEntity(new Message("User name or password is incorrect"), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = {UserNameAlreadyExistsException.class})
    public ResponseEntity<Message> exceptionHandling(UserNameAlreadyExistsException e) {
        return new ResponseEntity(new Message(e.getMessage()), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = {InputDataValidationException.class})
    public ResponseEntity<Message> exceptionHandling(InputDataValidationException e) {
        return new ResponseEntity(new Message(e.getMessage()), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Message> exceptionHandling(Exception e) {
        return new ResponseEntity(new Message(e.getMessage()), HttpStatus.FORBIDDEN);
    }
}