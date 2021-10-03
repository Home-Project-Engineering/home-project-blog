package com.homeproject.blog.backend.exceptions;

public class CommentNotFoundException extends Exception {

    public CommentNotFoundException() {
        super("The comment wasn't found");
    }
}
