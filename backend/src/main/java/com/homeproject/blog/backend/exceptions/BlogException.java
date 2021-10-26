package com.homeproject.blog.backend.exceptions;

import org.springframework.http.HttpStatus;

public class BlogException extends RuntimeException {
    private String code;
    private HttpStatus httpStatus;

    public BlogException(String message) {
        super(message);
    }

    public String getCode() {
        return code;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}