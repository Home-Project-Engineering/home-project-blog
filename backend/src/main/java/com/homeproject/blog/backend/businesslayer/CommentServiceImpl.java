package com.homeproject.blog.backend.businesslayer;

import com.homeproject.blog.backend.businesslayer.converters.UserConverter;
import com.homeproject.blog.backend.data.entity.CommentEntity;
import com.homeproject.blog.backend.businesslayer.converters.CommentConverter;
import com.homeproject.blog.backend.data.entity.PostEntity;
import com.homeproject.blog.backend.data.repository.CommentRepository;
import com.homeproject.blog.backend.dtos.Comment;
import com.homeproject.blog.backend.exceptions.CommentNotFoundException;
import com.homeproject.blog.backend.security.SecurityService;
import com.homeproject.blog.backend.supportclasses.CurrentDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository repository;
    @Autowired
    private CommentConverter commentConverter;
    @Autowired
    private PostService postService;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private UserConverter userConverter;

    @Override
    public Comment createComment(Comment comment, Long postId) {
        CommentEntity entity = new CommentEntity();
        PostEntity postEntity = postService.findPostEntity(postId);
        entity.setAuthor(userConverter.userToEntity(securityService.findLoggedInUser()));
        entity.setText(comment.getText());
        entity.setPost(postEntity);
        String date = CurrentDate.getDate();
        entity.setCreatedOn(date);
        entity.setUpdatedOn(date);
        CommentEntity updatedEntity = repository.save(entity);
        return commentConverter.entityToComment(updatedEntity);
    }

    private CommentEntity verifyCommentExisting(Long id) {
        Optional<CommentEntity> result = repository.findById(id);
        if (result.isEmpty()) {
            throw new CommentNotFoundException();
        }
        return result.get();
    }

    @Override
    public Comment updateComment(Long id, Comment changes) {
        CommentEntity entity = verifyCommentExisting(id);
        entity.setText(changes.getText());
        entity.setUpdatedOn(CurrentDate.getDate());
        CommentEntity updatedEntity = repository.save(entity);
        return commentConverter.entityToComment(updatedEntity);
    }

    @Override
    public Comment readComment(Long id, Long postId) {
        CommentEntity entity = verifyCommentExisting(id);
        return commentConverter.entityToComment(entity);
    }

    @Override
    public void deleteComment(Long id, Long postId) {
        verifyCommentExisting(id);
        repository.deleteById(id);
    }

    @Override
    public Page<Comment> findAll(Long postId, Long id, String authorName, Integer pageNum, Integer pageSize, String sort) {
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
        pageNum = pageNum == null ? pageNum = 0 : pageNum;
        pageSize = pageSize == null ? pageSize = 10 : pageSize;
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize, sorting);
        Page<CommentEntity> allByIdAndName = repository.findAllByIdAndName(pageRequest, postId, id, authorName);
        Page<Comment> page = new PageImpl<>(allByIdAndName.stream().map(commentConverter::entityToComment).collect(Collectors.toList()), pageRequest, allByIdAndName.getTotalElements());
        return page;
    }
}
