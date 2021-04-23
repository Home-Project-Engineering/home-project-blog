package com.itacademy.blog.data.entity;

public enum Permission{
    USER_MANAGEMENT("users"),
    TAG_REMOVE("tags:remove"),
    POST_UPDATE("posts:update"),
    POST_DELETE("posts:delete"),
    COMMENTS_UPDATE("comments:update"),
    COMMENTS_DELETE("comments:delete");
    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }
    public String getPermission() {
        return permission;
    }
}
