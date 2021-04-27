package com.softserveinc.ita.homeprojectblog.exceptions;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.softserveinc.ita.homeprojectblog.generated.model.Error;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Error> generalException(Exception exception) {
        var error = new Error();
        error.setCode("404");
        error.setMessage("General Exception. " + exception);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<Error> userException(NoSuchUserException exception) {
        var error = new Error();
        error.setCode("400");
        error.setMessage(exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<Error> usersException(NoSuchUsersException exception) {
        var error = new Error();
        error.setCode("400");
        error.setMessage(exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {DataIntegrityViolationException.class,
            org.hibernate.exception.ConstraintViolationException.class})
    public ResponseEntity<Object> hibernateValidationException(ConstraintViolationException e) {
        var error = new Error();
        error.setCode("400");
        error.setMessage(e.getCause().getMessage().split("Detail: ")[1]);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<Error> javaxValidationException(javax.validation.ConstraintViolationException e) {
        var error = new Error();
        error.setCode("400");
        error.setMessage(e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
