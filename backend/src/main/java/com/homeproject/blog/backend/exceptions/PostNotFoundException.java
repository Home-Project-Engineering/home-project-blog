package com.homeproject.blog.backend.exceptions;

import org.springframework.http.HttpStatus;

public class PostNotFoundException extends BlogException {

    public PostNotFoundException() {
        super("The post wasn't found");
        setCode("404");
        setHttpStatus(HttpStatus.NOT_FOUND);
    }
}
