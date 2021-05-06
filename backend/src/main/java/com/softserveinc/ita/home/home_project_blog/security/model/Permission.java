package com.softserveinc.ita.home.home_project_blog.security.model;

public enum Permission {
    USERS("users"),
    POSTS_CREATE("posts:create"),
    POSTS_UPDATE("posts:update"),
    COMMENTS_CREATE("comments:create"),
    COMMENTS_UPDATE("comments:update"),
    TAGS_DELETE("tags:delete");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
