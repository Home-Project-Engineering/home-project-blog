package com.homeproject.blog.backend.business.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundException extends RuntimeException{
    private final HttpStatus httpStatus = HttpStatus.NOT_FOUND;

    public NotFoundException() {
        super ("Exception 404. Entity not found.");
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
