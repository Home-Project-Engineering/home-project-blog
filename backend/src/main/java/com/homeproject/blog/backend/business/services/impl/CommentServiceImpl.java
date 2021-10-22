package com.homeproject.blog.backend.business.services.impl;

import com.homeproject.blog.backend.business.convertersBetweenServiceAndController.CommentConverter;
import com.homeproject.blog.backend.business.models.Author;
import com.homeproject.blog.backend.business.models.additional.Date;
import com.homeproject.blog.backend.business.services.CommentService;
import com.homeproject.blog.backend.business.models.DTO.Comment;
import com.homeproject.blog.backend.database.repositories.CommentRepository;
import com.homeproject.blog.backend.persistence.entity.CommentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@ComponentScan("com.homeproject.blog.backend.datalayer.repositories")
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    private CommentConverter commentConverter;

    @Override
    public Comment createComment(Comment comment) {
        CommentEntity entity = new CommentEntity();
        entity.setId(comment.getId());
        entity.setAuthor(new Author(comment.getAuthor()));
        entity.setText(comment.getText());
        entity.setCreatedOn(Date.getCurrentDate());
        entity.setUpdatedOn(Date.getCurrentDate());
        CommentEntity updatedEntity = commentRepository.save(entity);
        return commentConverter.entityToComment(updatedEntity);
    }

    private CommentEntity verifyCommentExisting(Long id){
        Optional<CommentEntity> result = commentRepository.findById(id);
        return result.get();
    }


    @Override
    public Comment updateComment(Long id, Comment changes){
        CommentEntity entity = verifyCommentExisting(id);
        entity.setText(changes.getText());
        entity.setUpdatedOn(Date.getCurrentDate());
        CommentEntity updatedEntity = commentRepository.save(entity);
        return commentConverter.entityToComment(updatedEntity);
    }

    @Override
    public Comment readComment(Long id) {
        CommentEntity entity = verifyCommentExisting(id);
        return commentConverter.entityToComment(entity);
    }

    @Override
    public Collection<Comment> getComments() {
        Iterable<CommentEntity> entities = commentRepository.findAll();
        ArrayList<CommentEntity> list = new ArrayList<>();
        entities.forEach(list::add);
        Stream<Comment> comments = list.stream().map(commentConverter::entityToComment);
        return comments.collect(Collectors.toCollection(ArrayList::new));
    }


    @Override
    public void deleteComment(Long id) {
        verifyCommentExisting(id);
        commentRepository.deleteById(id);
    }


    @Override
    public Comment getCommentById(Long id) {
        return null;
    }


}
