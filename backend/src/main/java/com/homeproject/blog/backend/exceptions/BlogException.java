package com.homeproject.blog.backend.exceptions;

import org.springframework.http.HttpStatus;

public class BlogException extends Exception{

    private String code;

    private HttpStatus httpStatus;
    public BlogException(String message) {
        super(message);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
