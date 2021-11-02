package com.homeproject.blog.backend.business.converters;

import com.homeproject.blog.backend.business.models.DTO.RoleTypeDTO;
import com.homeproject.blog.backend.business.models.DTO.UserDTO;
import com.homeproject.blog.backend.persistence.entity.RoleTypeEntity;
import com.homeproject.blog.backend.persistence.entity.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class UserConverter {

    public UserDTO entityToUser(UserEntity entity) {
        UserDTO user = new UserDTO();
        user.setId(entity.getId());
        user.setName(entity.getName());
        user.setFirstName(entity.getFirstName());
        user.setLastName(entity.getSecondName());
        user.setEmail(entity.getEmail());
        user.setPassword(entity.getPassword());
        RoleTypeEntity role = entity.getRole();
        user.setRole(new RoleTypeDTO(role.getId(), role.getName()));
        return user;
    }

    public UserEntity userToEntity(UserDTO user) {
        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setName(user.getName());
        entity.setFirstName(user.getFirstName());
        entity.setSecondName(user.getLastName());
        entity.setEmail(user.getEmail());
        entity.setPassword(user.getPassword());
        RoleTypeDTO role = user.getRole();
        entity.setRole(new RoleTypeEntity(role.getId(), role.getName()));
        return entity;
    }
}
