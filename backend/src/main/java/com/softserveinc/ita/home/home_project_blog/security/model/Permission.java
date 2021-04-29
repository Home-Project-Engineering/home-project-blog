package com.softserveinc.ita.home.home_project_blog.security.model;

public enum Permission {
    USERS("users"),
    POSTS_CREATE("posts:create"),
    POSTS_UPDATE("posts:update"),
    TAGS_DELETE("tags:delete");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
