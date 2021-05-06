package com.softserveinc.ita.home.home_project_blog.security.model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    ADMIN(Set.of(Permission.USERS, Permission.POSTS_CREATE, Permission.POSTS_UPDATE, Permission.COMMENTS_CREATE, Permission.COMMENTS_UPDATE, Permission.TAGS_DELETE)),
    MODERATOR(Set.of(Permission.POSTS_CREATE, Permission.POSTS_UPDATE, Permission.COMMENTS_CREATE, Permission.COMMENTS_UPDATE, Permission.TAGS_DELETE)),
    BLOGGER(Set.of(Permission.POSTS_CREATE, Permission.COMMENTS_CREATE));

    private final Set<Permission> permissions;

    Role() {
        this.permissions = Set.of();
    }

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities(){
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
