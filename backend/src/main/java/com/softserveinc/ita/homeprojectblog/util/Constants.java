package com.softserveinc.ita.homeprojectblog.util;

public final class Constants {
    // messages
    public static final String WRONG_PASSWORD_PATTERN = "The password must include small and large letters and at least one digit";
    public static final String WRONG_PASSWORD_SIZE = "The password should be between 8 and 255 characters.";

    // regexp
    public static final String PASSWORD_REGEXP = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[0-9a-zA-Z]{8,}";

    private Constants() {
    }
}
