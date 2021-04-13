package com.itacademy.blog.services.exception;

public abstract class BaseBlogException extends RuntimeException {

    public BaseBlogException(String message, Throwable cause) {
        super(message, cause);
    }

    protected BaseBlogException(String message) {
        super(message);
    }
}
