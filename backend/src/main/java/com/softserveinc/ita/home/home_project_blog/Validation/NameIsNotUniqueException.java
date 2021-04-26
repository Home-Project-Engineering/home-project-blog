package com.softserveinc.ita.home.home_project_blog.Validation;

public class NameIsNotUniqueException extends RuntimeException {
    public NameIsNotUniqueException() {
        super(Const.NAME_IS_NOT_UNIQUE);
    }

    public NameIsNotUniqueException(String message) {
        super(message);
    }
}
