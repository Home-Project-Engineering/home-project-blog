package com.homeproject.blog.backend.businesslayer.converters;

import com.homeproject.blog.backend.businesslayer.dto.AuthorDTO;
import com.homeproject.blog.backend.businesslayer.dto.CommentDTO;
import com.homeproject.blog.backend.businesslayer.dto.UserDTO;
import com.homeproject.blog.backend.data.entities.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

@Component
public class CommentToCommentDTOConverter implements BlogDTOConverter<Comment, CommentDTO> {

    @Lazy
    @Autowired
    ConversionService conversionService;

    @Override
    public CommentDTO convert(Comment source) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(source.getId());
        commentDTO.setAuthor(conversionService.convert(source.getAuthor(), AuthorDTO.class));
        commentDTO.setText(source.getText());
        commentDTO.setCreatedOn(source.getCreatedOn());
        commentDTO.setUpdatedOn(source.getUpdatedOn());
        return commentDTO;
    }
}
