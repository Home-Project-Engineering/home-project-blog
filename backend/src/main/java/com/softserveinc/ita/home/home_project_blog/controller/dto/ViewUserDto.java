package com.softserveinc.ita.home.home_project_blog.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ViewUserDto {
    private long id;
    private String name;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
}
