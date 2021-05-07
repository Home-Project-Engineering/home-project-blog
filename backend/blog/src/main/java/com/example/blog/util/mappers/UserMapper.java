package com.example.blog.backend.util.mappers;

import com.example.blog.backend.generated.model.Role;
import com.example.blog.backend.generated.model.User;
import com.example.blog.backend.repository.entities.RoleEntity;
import com.example.blog.backend.repository.entities.UserEntity;
import com.example.blog.backend.util.dtos.DtoRole;
import com.example.blog.backend.util.dtos.DtoUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.List;


@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);


    DtoUser fromUser(User user);

    UserEntity fromDto(DtoUser dto);

    @Mapping(target = "role", source = "roleEntity")
    DtoUser fromEntity(UserEntity entity);

    @Mapping(target = "password", constant = "********")
    User fromDtoToUser(DtoUser dto);

    DtoRole convertToDto(RoleEntity entity);

    Role convertToDto(DtoRole role);

    DtoRole convertToDto(Role role);

    RoleEntity convertToEntity(DtoRole dtoRole);


    List<User> toListUsers(List<DtoUser> dtoUserList);

    default Page<DtoUser> toPageDto(Page<UserEntity> entities) {
        return entities.map(this::fromEntity);
    }

}
