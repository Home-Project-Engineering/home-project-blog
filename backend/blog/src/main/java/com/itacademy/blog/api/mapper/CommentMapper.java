package com.itacademy.blog.api.mapper;

import com.itacademy.blog.model.Comment;
import com.itacademy.blog.services.DTO.CommentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CommentMapper {
CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);
    @Mapping(source = "user", target = "user")
    CommentDTO convert(Comment comment);
    @Mapping(source = "user", target = "user")
    Comment convert(CommentDTO commentDTO);
    List<CommentDTO> convert(List<Comment> commentEntities);
}
