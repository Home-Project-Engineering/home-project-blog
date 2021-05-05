package com.softserveinc.ita.homeprojectblog.security;

public enum Permission {
    ROLE_MANAGEMENT("role:management"),
    USER_MANAGEMENT("user:management"),
    TAG_REMOVE("tag:remove"),
    POST_UPDATE("posts:update"),
    POST_DELETE("posts:delete"),
    COMMENTS_UPDATE("comments:update"),
    COMMENTS_DELETE("comments:delete");

    private final String name;

    Permission(String permission) {
        this.name = permission;
    }

    public String getName() {
        return name;
    }
}
