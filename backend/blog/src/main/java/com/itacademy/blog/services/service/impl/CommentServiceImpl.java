package com.itacademy.blog.services.service.impl;

import com.itacademy.blog.data.entity.Comment;
import com.itacademy.blog.data.entity.User;
import com.itacademy.blog.data.repository.CommentRepo;
import com.itacademy.blog.data.repository.PostRepo;
import com.itacademy.blog.services.DTO.CommentDTO;
import com.itacademy.blog.services.exception.NotFoundBlogException;
import com.itacademy.blog.services.mapper.CommentMapper;
import com.itacademy.blog.services.mapper.UserMapper;
import com.itacademy.blog.services.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepo commentRepo;
    @Autowired
    PostRepo postRepo;

    @Override
    public CommentDTO createComment(Long postId, CommentDTO createCommentDto) {
        createCommentDto.setCreatedOn(OffsetDateTime.now());
        Comment entityToCreate = CommentMapper.INSTANCE.convert(createCommentDto);
        entityToCreate.setPost(postRepo.findById(postId).orElseThrow(() -> new EntityNotFoundException("Post with id:" + postId + " is not found")));
        commentRepo.save(entityToCreate);
        return CommentMapper.INSTANCE.convert(entityToCreate);
    }

    @Override
    public CommentDTO updateComment(Long postId, Long id, CommentDTO updateCommentDto) {
        Optional<Comment> optionalComment = commentRepo.findById(id);
        if (optionalComment.isPresent()) {
            Comment fromDB = optionalComment.get();
            if (updateCommentDto.getText() != null) {
                fromDB.setText(updateCommentDto.getText());
                fromDB.setUpdatedOn(OffsetDateTime.now());
            }
            commentRepo.save(fromDB);
            return CommentMapper.INSTANCE.convert(fromDB);
        } else {
            throw new EntityNotFoundException("Comment with id:" + id + " is not found");
        }
    }

    @Override
    public List<CommentDTO> findComments(Integer pageNumber, Integer pageSize, String sort, Specification<Comment> specification) {
        List<Comment> toReturn = commentRepo.findAll(specification, PageRequest
                .of(pageNumber - 1, pageSize, getSort(sort))).toList();

        return CommentMapper.INSTANCE.convert(toReturn);
    }
    private Sort getSort(String sort) {
        StringBuilder str = new StringBuilder(sort);

        if(str.charAt(0) == '-'){
            str.deleteCharAt(0);
            return Sort.by(Sort.Direction.DESC, str.toString());}

        return Sort.by(Sort.Direction.ASC, str.toString());
    }

    @Override
    public CommentDTO getCommentById(Long postId, Long id) {
        Comment toGet = commentRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comment with id:" + id + " is not found"));
        return CommentMapper.INSTANCE.convert(toGet);
    }

    @Override
    public CommentDTO deleteComment(Long postId, Long id) {
        return null;
    }
}
