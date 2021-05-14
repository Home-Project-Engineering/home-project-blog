package com.softserveinc.ita.homeprojectblog.security;

public enum Permission {
    BLOGGER_ROLE("role:any-registered"),
    MODERATOR_ROLE("role:moderator-admin"),
    ADMIN_ROLE("role:admin");


    private final String name;

    Permission(String permission) {
        this.name = permission;
    }

    public String getName() {
        return name;
    }

}
