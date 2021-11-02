package com.homeproject.blog.backend.business.services.impl;

import com.homeproject.blog.backend.presentation.converters.CommentConverter;
import com.homeproject.blog.backend.business.models.DTO.AuthorDTO;
import com.homeproject.blog.backend.business.models.additional.Date;
import com.homeproject.blog.backend.business.services.CommentService;
import com.homeproject.blog.backend.business.models.DTO.CommentDTO;
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
    public CommentDTO createComment(CommentDTO comment) {
        CommentEntity entity = new CommentEntity();
        entity.setId(comment.getId());
        entity.setAuthorDTO(new AuthorDTO(comment.getAuthorDTO()));
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
    public CommentDTO updateComment(Long id, CommentDTO changes){
        CommentEntity entity = verifyCommentExisting(id);
        entity.setText(changes.getText());
        entity.setUpdatedOn(Date.getCurrentDate());
        CommentEntity updatedEntity = commentRepository.save(entity);
        return commentConverter.entityToComment(updatedEntity);
    }

    @Override
    public CommentDTO readComment(Long id) {
        CommentEntity entity = verifyCommentExisting(id);
        return commentConverter.entityToComment(entity);
    }

    @Override
    public Collection<CommentDTO> getComments() {
        Iterable<CommentEntity> entities = commentRepository.findAll();
        ArrayList<CommentEntity> list = new ArrayList<>();
        entities.forEach(list::add);
        Stream<CommentDTO> comments = list.stream().map(commentConverter::entityToComment);
        return comments.collect(Collectors.toCollection(ArrayList::new));
    }


    @Override
    public void deleteComment(Long id) {
        verifyCommentExisting(id);
        commentRepository.deleteById(id);
    }


    @Override
    public CommentDTO getCommentById(Long id) {
        return null;
    }


}
