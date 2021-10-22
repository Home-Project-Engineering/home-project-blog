package com.homeproject.blog.backend.business.convertersBetweenServiceAndController;

import com.homeproject.blog.backend.persistence.entity.CommentEntity;
import com.homeproject.blog.backend.business.models.DTO.Comment;
import org.springframework.stereotype.Service;

@Service
public class CommentConverter {

    public Comment entityToComment(CommentEntity entity) {
        Comment comment = new Comment();
        comment.setId(entity.getId());
        comment.setText(entity.getText());
        return comment;
    }

    public CommentEntity commentToEntity(Comment comment) {
        CommentEntity entity = new CommentEntity();
        entity.setId(comment.getId());
        entity.setText(comment.getText());
        return entity;
    }
}
