package com.homeproject.blog.backend.exceptions;

import com.homeproject.blog.backend.businesslayer.dto.ErrorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(value
            = { CommentNotFoundException.class, ForbiddenActionException.class,
            PostNotFoundException.class, TagNotFoundException.class})
    protected ResponseEntity<Object> handleConflict(BlogException ex) {
        return new ResponseEntity<>(new ErrorDTO(ex.getCode(), ex.getMessage()), ex.getHttpStatus());
    }
}
