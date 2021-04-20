package com.softserveinc.ita.homeprojectblog.exceptions;

public class NoSuchUsersException extends RuntimeException{
    public NoSuchUsersException(String message) {
        super(message);
    }
}
