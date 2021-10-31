package com.homeproject.blog.backend.exceptions;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends BlogException {

    public UserNotFoundException() {
        super("The user wasn't found");
        setCode("404");
        setHttpStatus(HttpStatus.NOT_FOUND);
    }
}
