package com.softserveinc.ita.home.home_project_blog.validation;

public class EmailIsNotUniqueException extends RuntimeException {
    public EmailIsNotUniqueException() {
        super(Const.EMAIL_IS_NOT_UNIQUE);
    }

    public EmailIsNotUniqueException(String message) {
        super(message);
    }
}
