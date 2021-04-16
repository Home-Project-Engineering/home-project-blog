package com.softserveinc.ita.home.home_project_blog.models;

public class ResponseError {
    String code;
    String message;

    public ResponseError(String code, String message){
        this.code = code;
        this.message = message;
    }
}
