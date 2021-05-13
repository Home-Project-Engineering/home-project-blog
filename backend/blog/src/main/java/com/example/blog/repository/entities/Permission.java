package com.example.blog.repository.entities;

public enum Permission {


    CREATE_USER("create:user"),
    SEE_POST_COMMENT_TAG("see:post:comment:tag"),
    CUR_USER_ACTION("cur:user:action"),
    MANAGE_USER("manege:user"),
    CREATE_POST_COMMENT("create:post:comment"),
    DELETE_UPDATE_POST_COMMENT_TAG("delete:update:posts:comment:tag");


    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
