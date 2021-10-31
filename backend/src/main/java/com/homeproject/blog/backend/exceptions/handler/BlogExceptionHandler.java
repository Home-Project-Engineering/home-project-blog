package com.homeproject.blog.backend.exceptions.handler;

import com.homeproject.blog.backend.dtos.Error;
import com.homeproject.blog.backend.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.security.access.AccessDeniedException;


@ControllerAdvice
public class BlogExceptionHandler {
    @ExceptionHandler(value
            = { CommentNotFoundException.class, ForbiddenActionException.class,
                PostNotFoundException.class, TagNotFoundException.class, UserExistsException.class})
    protected ResponseEntity<Object> handleConflict(BlogException ex) {
        return new ResponseEntity<>(new Error(ex.getCode(), ex.getMessage()), ex.getHttpStatus());
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    protected ResponseEntity<Object> handleSecurityConflict(Exception ex) {
        return new ResponseEntity<>(new Error("403", ex.getMessage()), HttpStatus.FORBIDDEN);
    }
}
