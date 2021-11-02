package com.homeproject.blog.backend.exceptions;

import org.springframework.http.HttpStatus;

public class ForbiddenActionException extends BlogException {

    public ForbiddenActionException() {
        super("The action is forbidden");
        setCode("403");
        setHttpStatus(HttpStatus.FORBIDDEN);
    }
}
