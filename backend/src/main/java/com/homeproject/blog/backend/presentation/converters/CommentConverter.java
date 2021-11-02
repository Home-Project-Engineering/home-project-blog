package com.homeproject.blog.backend.presentation.converters;

import com.homeproject.blog.backend.persistence.entity.CommentEntity;
import com.homeproject.blog.backend.business.models.DTO.CommentDTO;
import org.springframework.stereotype.Service;

@Service
public class CommentConverter {

    public CommentDTO entityToComment(CommentEntity entity) {
        CommentDTO comment = new CommentDTO();
        comment.setId(entity.getId());
        comment.setText(entity.getText());
        return comment;
    }

    public CommentEntity commentToEntity(CommentDTO comment) {
        CommentEntity entity = new CommentEntity();
        entity.setId(comment.getId());
        entity.setText(comment.getText());
        return entity;
    }
}
