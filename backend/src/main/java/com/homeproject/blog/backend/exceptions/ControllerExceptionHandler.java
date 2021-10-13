package com.homeproject.blog.backend.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



@ControllerAdvice
public class ControllerExceptionHandler {

//    @ExceptionHandler(value
//            = {IllegalArgumentException.class, IllegalStateException.class})
//    protected ResponseEntity<Object> handleConflict(BlogException ex) {
//        return new ResponseEntity<>(new Error(ex.getCode(), ex.getMessage()),ex.getHttpStatus());
//    }


}
