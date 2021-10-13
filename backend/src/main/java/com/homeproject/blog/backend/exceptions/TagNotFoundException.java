package com.homeproject.blog.backend.exceptions;

import org.springframework.http.HttpStatus;

public class TagNotFoundException extends BlogException {

    public TagNotFoundException() {
        super("The tag wasn't found");
        setCode("404");
        setHttpStatus(HttpStatus.NOT_FOUND);
    }
}
