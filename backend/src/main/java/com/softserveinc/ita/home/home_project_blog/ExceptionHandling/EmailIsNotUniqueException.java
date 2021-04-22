package com.softserveinc.ita.home.home_project_blog.ExceptionHandling;

public class EmailIsNotUniqueException extends RuntimeException {
    public EmailIsNotUniqueException() {
        super(ErrorConst.EMAIL_IS_NOT_UNIQUE);
    }

    public EmailIsNotUniqueException(String message) {
        super(message);
    }
}
