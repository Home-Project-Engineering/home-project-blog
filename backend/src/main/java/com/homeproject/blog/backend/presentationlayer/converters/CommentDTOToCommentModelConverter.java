package com.homeproject.blog.backend.presentationlayer.converters;

import com.homeproject.blog.backend.businesslayer.dto.CommentDTO;
import com.homeproject.blog.backend.presentationlayer.model.Author;
import com.homeproject.blog.backend.presentationlayer.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

@Component
public class CommentDTOToCommentModelConverter implements BlogConverter<CommentDTO, Comment> {

    @Lazy
    @Autowired
    ConversionService conversionService;

    @Override
    public Comment convert(CommentDTO source) {
        Comment comment= new Comment();
        comment.setId(source.getId());
        comment.setAuthor(conversionService.convert(source.getAuthor(), Author.class));
        comment.setText(source.getText());
        comment.setCreatedOn(source.getCreatedOn());
        comment.setUpdatedOn(source.getUpdatedOn());
        return comment;
    }
}
