package com.homeproject.blog.backend.businesslayer.converters;

import com.homeproject.blog.backend.data.entity.UserEntity;
import com.homeproject.blog.backend.dtos.User;

public interface UserConverter {
    User entityToUser(UserEntity entity);

    UserEntity userToEntity(User user);
}
