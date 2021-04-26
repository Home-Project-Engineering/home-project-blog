package com.itacademy.blog.api.exception;

import com.itacademy.blog.model.Error;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;


@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<Object> validationError(MethodArgumentNotValidException e) {
        Error error = new Error();
        error.setCode("400");
        StringBuilder exception = new StringBuilder();
        for (FieldError fieldError  : e.getBindingResult().getFieldErrors()) {
            exception.append("ERROR: ").append(fieldError.getDefaultMessage()).append(";    \n");
        }
        error.setMessage(exception.toString());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<Object> validationException(ConstraintViolationException e) {

        Error error = new Error();
        error.setCode("400");
        error.setMessage("ERROR: " + e.getCause().getMessage().split("Detail: ")[1]);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = {org.springframework.data.mapping.PropertyReferenceException.class})
    public ResponseEntity<Object> validationException(PropertyReferenceException e) {
        Error error = new Error();
        error.setCode("400");
        error.setMessage(e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = { EntityNotFoundException.class})
    public ResponseEntity<Object> notFoundException(EntityNotFoundException e) {
        Error error = new Error();
        error.setCode("400");
        error.setMessage(e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> baseError(Exception e) {
        Error error = new Error();
        error.setCode("502");
        error.setMessage("ERROR: The unknown error appeard. Check your payload or contact support;");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /*
    @ExceptionHandler(value = {EntityExistsException.class})
    public ResponseEntity<Object> validationError(EntityExistsException e) {
        Error error = new Error();
        error.setCode("400");
        error.setMessage("ERROR: "+ e.getMessage()+ ";");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    */


/*
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<Object> validationException(MethodArgumentNotValidException e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        Error error = new Error(
                e.getLocalizedMessage(),
                "400",
                httpStatus
        );
        return new ResponseEntity<>(error, httpStatus);
    }

    @ExceptionHandler(value = {BaseBlogException.class})
    public ResponseEntity<Object> unknownException(BaseBlogException e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        Error error = new Error(
                e.getMessage(),
                "default",
                httpStatus
        );
        return new ResponseEntity<>(error, httpStatus);
    }

    @ExceptionHandler(value = {AlreadyExistBlogException.class})
    public ResponseEntity<Object> alreadyExistException(AlreadyExistBlogException e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        Error error = new Error(
                e.getMessage(),
                "400",
                httpStatus
        );
        return new ResponseEntity<>(error, httpStatus);
    }*/
}
