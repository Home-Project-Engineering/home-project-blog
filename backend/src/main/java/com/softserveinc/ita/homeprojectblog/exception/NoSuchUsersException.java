package com.softserveinc.ita.homeprojectblog.exception;

public class NoSuchUsersException extends RuntimeException{
    public NoSuchUsersException(String message) {
        super(message);
    }
}
