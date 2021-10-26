package com.homeproject.blog.backend.presentationlayer.converters;

import com.homeproject.blog.backend.businesslayer.dto.CommentDTO;
import com.homeproject.blog.backend.presentationlayer.model.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentModelToCommentDTOConverter implements BlogConverter<Comment, CommentDTO> {
    @Override
    public CommentDTO convert(Comment source) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setText(source.getText());
        return commentDTO;
    }
}
