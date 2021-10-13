package com.homeproject.blog.backend.businesslayer;

import com.homeproject.blog.backend.data.entity.CommentEntity;
import com.homeproject.blog.backend.data.entity.converters.AuthorConverter;
import com.homeproject.blog.backend.data.entity.converters.CommentConverter;
import com.homeproject.blog.backend.data.repository.CommentRepository;
import com.homeproject.blog.backend.dtos.Comment;
import com.homeproject.blog.backend.exceptions.CommentNotFoundException;
import com.homeproject.blog.backend.supportclasses.AppStartupRunner;
import com.homeproject.blog.backend.supportclasses.CurrentDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository repository;
    @Autowired
    private CommentConverter commentConverter;
    @Autowired
    private AuthorConverter authorConverter;

    @Override
    public Comment createComment(Comment comment) {
        CommentEntity entity = new CommentEntity();

        entity.setAuthor(AppStartupRunner.userEntity);
        entity.setText(comment.getText());
        String date = CurrentDate.getDate();
        entity.setCreatedOn(date);
        entity.setUpdatedOn(date);
        CommentEntity updatedEntity = repository.save(entity);
        return commentConverter.entityToComment(updatedEntity);
    }

    private CommentEntity verifyCommentExisting(Long id) throws CommentNotFoundException {
        Optional<CommentEntity> result = repository.findById(id);
        if (result.isEmpty()) {
            throw new CommentNotFoundException();
        }
        return result.get();
    }

    @Override
    public Comment updateComment(Long id, Comment changes) throws CommentNotFoundException {
        CommentEntity entity = verifyCommentExisting(id);
        entity.setText(changes.getText());
        entity.setUpdatedOn(CurrentDate.getDate());
        CommentEntity updatedEntity = repository.save(entity);
        return commentConverter.entityToComment(updatedEntity);
    }

    @Override
    public Comment readComment(Long id) throws CommentNotFoundException {
        CommentEntity entity = verifyCommentExisting(id);
        return commentConverter.entityToComment(entity);
    }

    @Override
    public Collection<Comment> getComments() {
        Iterable<CommentEntity> entities = repository.findAll();
        ArrayList<CommentEntity> list = new ArrayList<>();
        entities.forEach(list::add);
        Stream<Comment> comments = list.stream().map(commentConverter::entityToComment);
        return comments.collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public Collection<Comment> sortComments(Collection<Comment> comments, Map<String, String> parameters) {
        return comments;
    }

    @Override
    public void deleteComment(Long id) throws CommentNotFoundException {
        verifyCommentExisting(id);
        repository.deleteById(id);
    }
}
