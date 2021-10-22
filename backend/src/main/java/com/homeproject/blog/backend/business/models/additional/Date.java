package com.homeproject.blog.backend.business.models.additional;

public class Date {
    public static String getCurrentDate (){
        java.util.Date data = new java.util.Date();
        return data.toString();
    }
}
