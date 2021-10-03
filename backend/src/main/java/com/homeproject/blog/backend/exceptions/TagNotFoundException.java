package com.homeproject.blog.backend.exceptions;

public class TagNotFoundException extends Exception {

    public TagNotFoundException() {
        super("The tag wasn't found!");
    }
}
