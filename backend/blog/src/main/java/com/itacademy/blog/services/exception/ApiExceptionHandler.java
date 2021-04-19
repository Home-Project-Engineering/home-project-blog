package com.itacademy.blog.services.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

//to do
@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<Object> noSuchUserException(EntityNotFoundException e){
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        Error error = new Error(
                e.getMessage(),
                "404",
                httpStatus
        );
        return new ResponseEntity<>(error, httpStatus);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<Object> validationException(ConstraintViolationException e){
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        Error error = new Error(
                e.getConstraintViolations().toString(),
                "400",
                httpStatus
        );
        return new ResponseEntity<>(error, httpStatus);
    }
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<Object> validationException(MethodArgumentNotValidException e){
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

     Error error = new Error(
            e.getLocalizedMessage(),
                    "400",
                    httpStatus
        );
        return new ResponseEntity<>(error, httpStatus);
    }
    @ExceptionHandler(value = {BaseBlogException.class})
    public ResponseEntity<Object> unknownException(BaseBlogException e){
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        Error error = new Error(
                e.getMessage(),
                "default",
                httpStatus
        );
        return new ResponseEntity<>(error, httpStatus);
    }
    @ExceptionHandler(value = {AlreadyExistBlogException.class})
    public ResponseEntity<Object> alreadyExistException(AlreadyExistBlogException e){
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        Error error = new Error(
                e.getMessage(),
                "400",
                httpStatus
        );
        return new ResponseEntity<>(error, httpStatus);
    }
}
