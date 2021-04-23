package com.softserveinc.ita.home.home_project_blog.Validation;

public interface Const {
    String NOT_FOUND_USER_BY_ID = "User with id=%d wasn't found in the table 'users'.";
    String EMAIL_IS_NOT_UNIQUE = "Email is not unique.";
    String NAME_IS_NOT_UNIQUE = "Name is not unique.";
    String EMAIL_IS_NOT_VALID = "Email should be valid.";
    String WRONG_LENGTH = " must be between 1 and 255 characters.";
    String NAME_WRONG_LENGTH = "Name" + WRONG_LENGTH;
    String FIRST_NAME_WRONG_LENGTH = "First name" + WRONG_LENGTH;
    String LAST_NAME_WRONG_LENGTH = "Last name" + WRONG_LENGTH;
    String WRONG_PASSWORD = "Password should be between 8 and 200 characters.";
    String PASSWORD_PATTERN = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])";
}
