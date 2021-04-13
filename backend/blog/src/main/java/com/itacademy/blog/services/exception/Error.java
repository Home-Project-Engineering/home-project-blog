package com.itacademy.blog.services.exception;

import org.springframework.http.HttpStatus;

public class Error {

    private final String Message;
    private final String code;
    private final HttpStatus httpStatus;

    public String getMessage() {
        return Message;
    }

    public String getCode() {
        return code;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public Error(String message, String code, HttpStatus httpStatus) {
        Message = message;
        this.code = code;
        this.httpStatus = httpStatus;
    }
}
