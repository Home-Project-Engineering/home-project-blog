package com.homeproject.blog.backend.presentationlayer.controllers;

import com.homeproject.blog.backend.businesslayer.dto.CommentDTO;
import com.homeproject.blog.backend.businesslayer.services.CommentService;
import com.homeproject.blog.backend.presentationlayer.config.ParametersConfig;
import com.homeproject.blog.backend.presentationlayer.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CommentController implements PostsApi{

    @Autowired
    private CommentService commentService;

    @Autowired
    private ConversionService conversionService;

    @Override
    public ResponseEntity<Comment> createComment(Long postId, Comment comment){
        CommentDTO commentDTO = commentService.createComment(postId, conversionService.convert(comment, CommentDTO.class));
        return ResponseEntity.status(HttpStatus.CREATED).body(conversionService.convert(commentDTO, Comment.class));
    }

    @Override
    public ResponseEntity<List<Comment>> getComments(Long postId, Long id, String authorName, String sort, Integer pageNum, Integer pageSize) {
        Page<CommentDTO> page = commentService.getComments(postId, id, authorName, ParametersConfig.getSortParameters(pageNum, pageSize, sort));
        return ResponseEntity.ok().header("X-Total-Count", String.valueOf(page.getTotalElements())).body(page.stream().map(comment -> conversionService.convert(comment, Comment.class)).collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<Comment> getComment(Long postId, Long id){
        return ResponseEntity.ok(conversionService.convert(commentService.readComment(postId, id), Comment.class));
    }

    @Override
    public ResponseEntity<Comment> updateComment(Long postId, Long id, Comment comment) {
        CommentDTO commentDTO = commentService.updateComment(postId, id, conversionService.convert(comment, CommentDTO.class));
        return ResponseEntity.ok(conversionService.convert(commentDTO, Comment.class));
    }

    @Override
    public ResponseEntity<Void> removeComment(Long postId, Long id) {
        commentService.deleteComment(postId, id);
        return ResponseEntity.noContent().build();
    }
}
