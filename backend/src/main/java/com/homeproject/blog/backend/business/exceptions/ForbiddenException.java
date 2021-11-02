package com.homeproject.blog.backend.business.exceptions;

import org.springframework.http.HttpStatus;

public class ForbiddenException extends RuntimeException{
        private final HttpStatus httpStatus = HttpStatus.FORBIDDEN;

        public ForbiddenException() {
            super("Exception 403. Forbidden.");
        }


        public HttpStatus getHttpStatus() {
            return httpStatus;
        }
}
