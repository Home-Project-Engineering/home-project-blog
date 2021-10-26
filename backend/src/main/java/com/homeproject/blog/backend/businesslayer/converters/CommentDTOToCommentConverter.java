package com.homeproject.blog.backend.businesslayer.converters;

import com.homeproject.blog.backend.businesslayer.dto.CommentDTO;
import com.homeproject.blog.backend.data.entities.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentDTOToCommentConverter implements BlogDTOConverter<CommentDTO, Comment> {

    @Override
    public Comment convert(CommentDTO source) {
        Comment comment = new Comment();
        comment.setText(source.getText());
        return comment;
    }
}
