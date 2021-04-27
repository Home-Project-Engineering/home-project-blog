package com.softserveinc.ita.home.home_project_blog.validation;

import org.springframework.http.HttpStatus;

public class NotUniqueException extends RuntimeException {
    private HttpStatus httpStatus;
    public NotUniqueException(String msg) {
        this(msg, HttpStatus.BAD_REQUEST);
    }
    public NotUniqueException(String msg, HttpStatus httpStatus) {
        super(msg);
        this.httpStatus = httpStatus;
    }
 }
