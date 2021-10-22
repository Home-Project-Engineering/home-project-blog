package com.homeproject.blog.backend.businesslayer;

import com.homeproject.blog.backend.data.entity.CommentEntity;
import com.homeproject.blog.backend.businesslayer.converters.AuthorConverter;
import com.homeproject.blog.backend.businesslayer.converters.CommentConverter;
import com.homeproject.blog.backend.data.entity.TagEntity;
import com.homeproject.blog.backend.data.repository.CommentRepository;
import com.homeproject.blog.backend.dtos.Comment;
import com.homeproject.blog.backend.dtos.Tag;
import com.homeproject.blog.backend.exceptions.CommentNotFoundException;
import com.homeproject.blog.backend.supportclasses.AppStartupRunner;
import com.homeproject.blog.backend.supportclasses.CurrentDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    public void deleteComment(Long id) throws CommentNotFoundException {
        verifyCommentExisting(id);
        repository.deleteById(id);
    }

    @Override
    public Page<Comment> findAll(Long id, String authorName, Integer pageNum, Integer pageSize, String sort) {
        Sort sorting;
        if (sort != null) {
            if (sort.charAt(0) == '-') {
                sorting = Sort.by(sort.substring(1)).descending();
            } else {
                sorting = Sort.by(sort);
            }
        } else {
            sorting = Sort.by("id").descending();
        }
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize, sorting);
        Page<CommentEntity> allByIdAndName = repository.findAllByIdAndName(pageRequest, id, authorName);
        Page<Comment> page = new PageImpl<>(allByIdAndName.stream().map(commentConverter::entityToComment).collect(Collectors.toList()), pageRequest, allByIdAndName.getTotalElements());
        return page;
    }
}
