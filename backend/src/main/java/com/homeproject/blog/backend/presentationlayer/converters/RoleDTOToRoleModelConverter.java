package com.homeproject.blog.backend.presentationlayer.converters;

import com.homeproject.blog.backend.businesslayer.dto.RoleDTO;
import com.homeproject.blog.backend.presentationlayer.model.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleDTOToRoleModelConverter implements BlogConverter<RoleDTO, Role> {
    @Override
    public Role convert(RoleDTO source) {
        Role role= new Role();
        role.setName(Role.NameEnum.valueOf(source.getName()));
        return role;
    }
}
