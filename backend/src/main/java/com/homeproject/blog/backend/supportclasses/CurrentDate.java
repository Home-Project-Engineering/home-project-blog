package com.homeproject.blog.backend.supportclasses;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrentDate {
    public static String getDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        return formatter.format(date);
    }
}
