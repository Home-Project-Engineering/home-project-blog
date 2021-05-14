package com.softserveinc.ita.home.home_project_blog.controller.dto;

import com.softserveinc.ita.home.home_project_blog.security.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleDto {
    private String name;
}
