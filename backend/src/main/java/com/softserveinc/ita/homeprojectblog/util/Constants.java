package com.softserveinc.ita.homeprojectblog.util;

public interface Constants {
    // messages
    String WRONG_PASSWORD_PATTERN = "The password must include small and large letters and at least one digit";
    String WRONG_PASSWORD_SIZE = "The password should be between 8 and 255 characters.";

    // regexp
    String PASSWORD_REGEXP = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[0-9a-zA-Z]{8,}";
}
