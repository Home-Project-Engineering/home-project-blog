package com.itacademy.blog.services.mapper;

import com.itacademy.blog.data.entity.Comment;
import com.itacademy.blog.data.entity.Post;
import com.itacademy.blog.services.DTO.CommentDTO;
import com.itacademy.blog.services.DTO.PostDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CommentMapper {
CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);
    CommentDTO convert(Comment comment);
    Comment convert(CommentDTO commentDTO);
    List<CommentDTO> convert(List<Comment> commentEntities);
}
