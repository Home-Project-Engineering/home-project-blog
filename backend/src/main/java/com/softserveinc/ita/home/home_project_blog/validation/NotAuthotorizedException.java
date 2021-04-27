package com.softserveinc.ita.home.home_project_blog.validation;

public class NotAuthotorizedException extends RuntimeException{
    public NotAuthotorizedException() {
        super(Const.NO_AUTH);
    }
}
