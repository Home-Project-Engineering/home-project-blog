package com.homeproject.blog.backend.business.models.additional;

public enum Role {
    admin,
    moderator,
    blogger;

    public String fromValue(String name) {
        return name;
    }
}
