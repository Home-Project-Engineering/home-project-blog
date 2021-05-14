package com.softserveinc.ita.homeprojectblog.security;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum Permission {
    BLOGGER_ROLE("role:any-registered"),
    MODERATOR_ROLE("role:moderator-admin"),
    ADMIN_ROLE("role:admin");

    String name;

    Permission(String permission) {
        this.name = permission;
    }

    public String getName() {
        return name;
    }

}
