package com.homeproject.blog.backend.businesslayer.converters;

import com.homeproject.blog.backend.businesslayer.dto.RoleDTO;
import com.homeproject.blog.backend.data.entities.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleToRoleDTOConverter implements BlogDTOConverter<Role, RoleDTO> {
    @Override
    public RoleDTO convert(Role source) {
        RoleDTO roleDTO = new RoleDTO(source.getName().toString());
        return roleDTO;
    }
}
