package com.softserveinc.ita.home.home_project_blog.validation;

import org.springframework.http.HttpStatus;

public class WrongPasswordException extends RuntimeException {
    private HttpStatus httpStatus;

    public WrongPasswordException() {
        this(Const.PASSWORD_IS_WORNG);
    }

    public WrongPasswordException(String msg) {
        this(msg, HttpStatus.BAD_REQUEST);
    }

    public WrongPasswordException(String msg, HttpStatus httpStatus) {
        super(msg);
        this.httpStatus = httpStatus;
    }
}
