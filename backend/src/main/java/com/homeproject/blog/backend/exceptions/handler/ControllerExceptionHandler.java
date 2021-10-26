package com.homeproject.blog.backend.exceptions.handler;

import com.homeproject.blog.backend.exceptions.*;
import com.homeproject.blog.backend.presentationlayer.model.Error;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class ControllerExceptionHandler {
    
    @ExceptionHandler(value
            = {CommentNotFoundException.class, ForbiddenActionException.class,
            PostNotFoundException.class, TagNotFoundException.class, InternalServerErrorException.class})
    protected ResponseEntity<Error> handleConflict(BlogException ex) {
        Error error = new Error();
        error.setCode(ex.getCode());
        error.setMessage(ex.getMessage());
        return new ResponseEntity<>(error, ex.getHttpStatus());
    }
//    @ExceptionHandler
//    protected ResponseEntity<Error> handleMismatchException(MethodArgumentTypeMismatchException ex){
//
//        return null;
//    }
}
