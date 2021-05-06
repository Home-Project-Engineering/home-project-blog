package com.softserveinc.ita.home.home_project_blog.validation;

import org.springframework.http.HttpStatus;

public class CommentDoesntAdhereToThePostException extends RuntimeException {
    private HttpStatus httpStatus;

    public CommentDoesntAdhereToThePostException() {
        this(Const.COMMENT_DOESNT_ADHERE_TO_THE_POST);
    }

    public CommentDoesntAdhereToThePostException(String msg) {
        this(msg, HttpStatus.BAD_REQUEST);
    }

    public CommentDoesntAdhereToThePostException(String msg, HttpStatus httpStatus) {
        super(msg);
        this.httpStatus = httpStatus;
    }
}
