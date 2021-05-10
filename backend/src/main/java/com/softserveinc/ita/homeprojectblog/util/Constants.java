package com.softserveinc.ita.homeprojectblog.util;

public final class Constants {
    // messages
    public static final String WRONG_PASSWORD_PATTERN =
            "The password must include small and large letters and at least one digit";
    public static final String WRONG_PASSWORD_SIZE = "The password should be between 8 and 32 characters";

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

    private Constants() {
    }
}
