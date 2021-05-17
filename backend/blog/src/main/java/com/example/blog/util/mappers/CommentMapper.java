package com.example.blog.util.mappers;


import com.example.blog.generated.model.Comment;
import com.example.blog.repository.entities.CommentEntity;
import com.example.blog.repository.entities.UserEntity;
import com.example.blog.util.dtos.DtoComment;
import com.example.blog.util.dtos.DtoUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    DtoComment toDto(Comment comment);

    CommentEntity toEntity(DtoComment dtoComment);

//    @Mapping(target = "user", source = "author")
//    @Mapping(target = "user.password", constant = "******")
    Comment toModel(DtoComment dtoComment);

//    @Mapping(target = "role", source = "roleEntity")
    DtoUser fromEntity(UserEntity entity);


    DtoComment toDtoFromEntity(CommentEntity entity);

    List<Comment> toModels(List<DtoComment> dtoComments);

    default Page<DtoComment> toPageDto(Page<CommentEntity> entities) {
        return entities.map(this::toDtoFromEntity);
    }
}
