package com.homeproject.blog.backend.exceptions;

import org.springframework.http.HttpStatus;

public class TagNotFoundException extends Exception {
    private final String code = "404";
    private final HttpStatus httpStatus = HttpStatus.NOT_FOUND;

    public TagNotFoundException() {
        super("The tag wasn't found");
    }

    public String getCode() {
        return code;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
