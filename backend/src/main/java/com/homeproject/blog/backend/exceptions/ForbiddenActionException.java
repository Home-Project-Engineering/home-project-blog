package com.homeproject.blog.backend.exceptions;

import org.springframework.http.HttpStatus;

public class ForbiddenActionException extends Exception {
    private final String code = "403";
    private final HttpStatus httpStatus = HttpStatus.FORBIDDEN;

    public ForbiddenActionException() {
        super("The action is forbidden");
    }

    public String getCode() {
        return code;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
