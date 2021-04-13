package com.itacademy.blog.services.exception;

public class NotFoundBlogException extends BaseBlogException {

    public NotFoundBlogException(String message) {
        super(message);
    }

    public NotFoundBlogException(String message, Throwable cause) {
        super(message, cause);
    }
}
