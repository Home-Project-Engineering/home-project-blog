package com.homeproject.blog.backend.supportclasses;

import java.time.OffsetDateTime;

public class CurrentDate {

    public static String getDate() {
        return OffsetDateTime.now().toString();
    }
}
