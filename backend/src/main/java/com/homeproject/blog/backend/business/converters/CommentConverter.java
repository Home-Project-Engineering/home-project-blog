package com.homeproject.blog.backend.business.converters;

import com.homeproject.blog.backend.business.models.DTO.CommentDTO;
import com.homeproject.blog.backend.persistence.entity.CommentEntity;
import org.springframework.stereotype.Service;

@Service
public class CommentConverter {

        AuthorConverter authorConverter = new AuthorConverter();

        public CommentDTO entityToComment(CommentEntity entity) {
            CommentDTO comment = new CommentDTO();
            comment.setId(entity.getId());
            comment.setCreatedOn(entity.getCreatedOn());
            comment.setAuthorDTO(authorConverter.entityToAuthor(entity.getAuthorDTO()));
            comment.setText(entity.getText());
            comment.setPostId(entity.getPost().getId());
            comment.setUpdatedOn(entity.getUpdatedOn());
            return comment;
        }

        public CommentEntity commentToEntity(CommentDTO comment) {
            CommentEntity entity = new CommentEntity();
            entity.setId(comment.getId());
            entity.setCreatedOn(comment.getCreatedOn());
            entity.setAuthorDTO(authorConverter.authorToEntity(comment.getAuthorDTO()));
            entity.setText(comment.getText());
            entity.setUpdatedOn(comment.getUpdatedOn());
            return entity;
        }
}
