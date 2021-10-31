package com.homeproject.blog.backend.businesslayer.converters;

import com.homeproject.blog.backend.data.entity.CommentEntity;
import com.homeproject.blog.backend.dtos.Comment;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        comment.setPostId(entity.getPost().getId());
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
