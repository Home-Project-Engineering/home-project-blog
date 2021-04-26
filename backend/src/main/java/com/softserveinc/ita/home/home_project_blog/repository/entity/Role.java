package com.softserveinc.ita.home.home_project_blog.repository.entity;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    ADMIN,//(Set.of(Permission.USERS_WRITE, Permission.USERS_READ)),
    MODERATOR,
    BLOGGER,
    ANY;

//    private final Set<Permission> permissions;
//
//    Role() {
//        this.permissions = Set.of();
//    }
//
//    Role(Set<Permission> permissions) {
//        this.permissions = permissions;
//    }
//
//    public Set<Permission> getPermissions() {
//        return permissions;
//    }
//
//    public Set<SimpleGrantedAuthority> getAuthorities(){
//        return getPermissions().stream()
//                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
//                .collect(Collectors.toSet());
//    }
}
