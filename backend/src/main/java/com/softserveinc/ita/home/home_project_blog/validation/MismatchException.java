package com.softserveinc.ita.home.home_project_blog.validation;

import org.springframework.http.HttpStatus;

public class MismatchException extends RuntimeException {
    private HttpStatus httpStatus;

    public MismatchException(String msg) {
        this(msg, HttpStatus.BAD_REQUEST);
    }

    public MismatchException(String msg, HttpStatus httpStatus) {
        super(msg);
        this.httpStatus = httpStatus;
    }
}
