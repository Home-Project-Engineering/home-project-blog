package com.homeproject.blog.backend.exceptions;

import org.springframework.http.HttpStatus;

public class UserExistsException extends BlogException {

    public UserExistsException() {
        super("User's name already exists");
        setCode("409");
        setHttpStatus(HttpStatus.CONFLICT);
    }
}
