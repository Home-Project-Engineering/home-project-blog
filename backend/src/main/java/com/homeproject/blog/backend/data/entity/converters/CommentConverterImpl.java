package com.homeproject.blog.backend.data.entity.converters;

import com.homeproject.blog.backend.data.entity.CommentEntity;
import com.homeproject.blog.backend.dtos.Comment;
import org.springframework.stereotype.Service;

@Service
public class CommentConverterImpl implements CommentConverter {
    AuthorConverterImpl authorConverter = new AuthorConverterImpl();

    @Override
    public Comment entityToComment(CommentEntity entity) {
        Comment comment = new Comment();
        comment.setId(entity.getId());
        comment.setCreatedOn(entity.getCreatedOn());
        comment.setAuthor(authorConverter.entityToAuthor(entity.getAuthor()));
        comment.setText(entity.getText());
        comment.setUpdatedOn(entity.getUpdatedOn());
        return comment;
    }

    @Override
    public CommentEntity commentToEntity(Comment comment) {
        CommentEntity entity = new CommentEntity();
        entity.setId(comment.getId());
        entity.setCreatedOn(comment.getCreatedOn());
        entity.setAuthor(authorConverter.authorToEntity(comment.getAuthor()));
        entity.setText(comment.getText());
        entity.setUpdatedOn(comment.getUpdatedOn());
        return entity;
    }
}
