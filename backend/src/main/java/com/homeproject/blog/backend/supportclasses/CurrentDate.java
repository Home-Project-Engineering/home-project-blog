package com.homeproject.blog.backend.supportclasses;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrentDate {
    private static final SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

    public static String getDate() {
        Date date = new Date();
        return formatter.format(date);
    }
}
