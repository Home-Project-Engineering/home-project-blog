package com.homeproject.blog.backend.exceptions.handler;

import com.homeproject.blog.backend.exceptions.*;
import com.homeproject.blog.backend.presentationlayer.model.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.naming.AuthenticationException;
import javax.validation.ValidationException;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = {CommentNotFoundException.class, PostNotFoundException.class, TagNotFoundException.class, UserNotFoundException.class, RoleNotFoundException.class})
    protected ResponseEntity<Error> handleConflict(BlogException ex) {
        Error error = new Error();
        error.setCode(ex.getCode());
        error.setMessage(ex.getMessage());
        return new ResponseEntity<>(error, ex.getHttpStatus());
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    protected ResponseEntity<Error> handleMismatchException() {
        Error error = new Error();
        error.setCode("400");
        error.setMessage("The payload contains an error.");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationException.class)
    protected ResponseEntity<Error> handleError401() {
        Error error = new Error();
        error.setCode("401");
        error.setMessage("Unauthorized.");
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<Error> handleError403() {
        Error error = new Error();
        error.setCode("403");
        error.setMessage("Forbidden.");
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ValidationException.class)
    protected ResponseEntity<Error> handleValidationError( ValidationException ex) {
        Error error = new Error();
        error.setCode("409");
        error.setMessage(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Throwable.class)
    protected ResponseEntity<Object> handleControllerException() {
        Error error = new Error();
        error.setCode("500");
        error.setMessage("The unknown error appeard. Check your payload or contact support.");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
