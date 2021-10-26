package com.homeproject.blog.backend.presentationlayer.converters;

import com.homeproject.blog.backend.businesslayer.dto.CommentDTO;
import com.homeproject.blog.backend.presentationlayer.model.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentDTOToCommentModelConverter implements BlogConverter<CommentDTO, Comment> {
    @Override
    public Comment convert(CommentDTO source) {
        return null;
    }
}
