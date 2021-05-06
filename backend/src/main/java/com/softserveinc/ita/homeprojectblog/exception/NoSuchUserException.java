package com.softserveinc.ita.homeprojectblog.exception;

public class NoSuchUserException extends RuntimeException {
    public NoSuchUserException(String message) {
        super(message);
    }
}
