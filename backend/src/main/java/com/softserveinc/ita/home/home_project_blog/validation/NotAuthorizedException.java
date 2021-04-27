package com.softserveinc.ita.home.home_project_blog.validation;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AuthorizationServiceException;

@Getter
public class NotAuthorizedException extends AuthorizationServiceException{
    private HttpStatus httpStatus;
    public NotAuthorizedException() {
        this(Const.NO_AUTH, HttpStatus.BAD_REQUEST);
    }
    public NotAuthorizedException(String msg, HttpStatus httpStatus) {
        super(msg);
        this.httpStatus = httpStatus;
    }
}
