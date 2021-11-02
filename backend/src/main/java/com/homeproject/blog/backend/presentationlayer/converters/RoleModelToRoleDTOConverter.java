package com.homeproject.blog.backend.presentationlayer.converters;

import com.homeproject.blog.backend.businesslayer.dto.RoleDTO;
import com.homeproject.blog.backend.presentationlayer.model.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleModelToRoleDTOConverter implements BlogConverter<Role, RoleDTO> {
    @Override
    public RoleDTO convert(Role source) {
        return new RoleDTO(source.getName().name());
    }
}
