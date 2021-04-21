package com.itacademy.blog.services.exception;

public class AlreadyExistBlogException extends BaseBlogException {

    public AlreadyExistBlogException(String message) {
        super(message);
    }

    public AlreadyExistBlogException(String message, Throwable cause) {
        super(message, cause);
    }
}