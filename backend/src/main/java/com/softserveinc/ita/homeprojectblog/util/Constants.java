package com.softserveinc.ita.homeprojectblog.util;

public final class Constants {
    // message
    public static final String WRONG_PASSWORD_PATTERN =
            "The password must include small and large letters and at least one digit\n" +
                    "also the password should be between 8 and 32 characters";
    public static final String USER_NOT_EXIST = "User does not exist";
    public static final String INCORRECT_OLD_PASSWORD = "Old password is incorrect";
    public static final String POSTS_NOT_FOUND = "Posts not found in Database";

    // regexp
    public static final String PASSWORD_REGEXP = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{8,32}";


    // format
    public static final String COMMENT_OF_POST_NOT_FOUND_FORMAT =
            "Comment with id -->  %s, in post with id --> %s --> is not found in Database";
    public static final String COMMENT_OF_USER_NOT_FOUND_FORMAT =
            "Comment for current user with id --> %s and with comment id -->  %s --> is not found in Database";
    public static final String POST_OF_USER_NOT_FOUND_FORMAT =
            "Post for current user with id --> %s and with post id -->  %s --> is not found in Database";
    public static final String POST_NOT_FOUND_FORMAT = "Post with id --> %s --> is not found in Database";
    public static final String TAG_NOT_FOUND_FORMAT = "Tag with id --> %s --> is not found in Database";
    public static final String USER_NOT_FOUND_FORMAT = "There is no user with ID --> %s in Database";
    public static final String USER_ROLE_NOT_EXIST_FORMAT = "Role for current user --> %s does not exist";
    public static final String ROLE_NOT_EXIST_FORMAT = "Role with name --> %s does not exist";


    private Constants() {
    }
}
