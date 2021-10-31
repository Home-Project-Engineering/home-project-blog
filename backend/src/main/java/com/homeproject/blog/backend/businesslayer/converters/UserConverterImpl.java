package com.homeproject.blog.backend.businesslayer.converters;

import com.homeproject.blog.backend.data.entity.RoleTypeEntity;
import com.homeproject.blog.backend.data.entity.UserEntity;
import com.homeproject.blog.backend.dtos.RoleType;
import com.homeproject.blog.backend.dtos.User;
import org.springframework.stereotype.Service;

@Service
public class UserConverterImpl implements UserConverter{
    @Override
    public User entityToUser(UserEntity entity) {
        User user = new User();
        user.setId(entity.getId());
        user.setName(entity.getName());
        user.setFirstName(entity.getFirstName());
        user.setLastName(entity.getSecondName());
        user.setEmail(entity.getEmail());
        user.setPassword(entity.getPassword());
        RoleTypeEntity role = entity.getRole();
        user.setRole(new RoleType(role.getId(), role.getName()));
        return user;
    }

    @Override
    public UserEntity userToEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setName(user.getName());
        entity.setFirstName(user.getFirstName());
        entity.setSecondName(user.getLastName());
        entity.setEmail(user.getEmail());
        entity.setPassword(user.getPassword());
        RoleType role = user.getRole();
        entity.setRole(new RoleTypeEntity(role.getId(), role.getName()));
        return entity;
    }
}
