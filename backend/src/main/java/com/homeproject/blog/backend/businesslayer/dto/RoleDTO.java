package com.homeproject.blog.backend.businesslayer.dto;


public class RoleDTO {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RoleDTO(String name) {
        this.name = name;
    }

    //    @Enumerated(EnumType.STRING)
//    private RoleName name;
//
//    public enum RoleName {
//        BLOGGER, MODERATOR, ADMIN
//    }
//
//    public RoleName getName() {
//        return name;
//    }
//
//    public void setName(RoleName name) {
//        this.name = name;
//    }
//
//    public RoleDTO(RoleName name) {
//        this.name = name;
//    }
}
