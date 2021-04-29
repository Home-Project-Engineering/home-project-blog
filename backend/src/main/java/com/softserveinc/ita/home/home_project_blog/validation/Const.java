package com.softserveinc.ita.home.home_project_blog.validation;

public interface Const {
    String USER_DOESNT_EXIST = "User doesn't exist.";
    String POST_DOESNT_EXIST = "Post doesn't exist.";
    String NO_AUTH = "User wasn't authorized.";
    String EMAIL_IS_NOT_UNIQUE = "User with such email already exists.";
    String NAME_IS_NOT_UNIQUE = "User with such name already exists.";
    String EMAIL_IS_NOT_VALID = "Email should be valid.";
    String WRONG_LENGTH = " must be between 1 and 255 characters.";
    String NAME_WRONG_LENGTH = "Name must be between 4 and 255 characters.";
    String FIRST_NAME_WRONG_LENGTH = "First name" + WRONG_LENGTH;
    String LAST_NAME_WRONG_LENGTH = "Last name" + WRONG_LENGTH;
    String WRONG_PASSWORD_LENGTH = "Password should contain at least 8 characters.";
    String WRONG_PASSWORD = "Password should contain at least one figure, one lowercase and one uppercase letter. "
            + WRONG_PASSWORD_LENGTH;
    String PASSWORD_PATTERN = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[0-9a-zA-Z]{8,}";
}
