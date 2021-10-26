package com.homeproject.blog.backend.exceptions;

import org.springframework.http.HttpStatus;

public class InternalServerErrorException extends BlogException{

    public InternalServerErrorException() {
        super("Oops......");
        setCode("500");
        setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
