package com.softserveinc.ita.homeprojectblog.security;

public enum Permission {
    REGISTERED_ROLE("role:registered"),
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
