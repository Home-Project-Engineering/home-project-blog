package com.homeproject.blog.backend.exceptions;

public class PostNotFoundException extends Exception {

    public PostNotFoundException() {
        super("The post wasn't found");
    }
}
