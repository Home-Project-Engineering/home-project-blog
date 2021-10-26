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

    @GetMapping(produces = "application/json")
    public ResponseEntity<Object> getComments(@PathVariable Long post_id, @RequestParam( name = "id", required = false) Long id, @RequestParam(name = "author_name", required = false) String authorName, @RequestParam(name = "sort", required = false, defaultValue = "-id") String sort, @RequestParam(name = "page_num", required = false, defaultValue = "0") Integer pageNum, @RequestParam(name = "page_size", required = false, defaultValue = "10") Integer pageSize) {
        Page<CommentDTO> page = commentService.getComments(post_id, id, authorName, ParametersConfig.getSortParameters(pageNum, pageSize, sort));
        return ResponseEntity.ok().header("X-Total-Count", String.valueOf(page.getTotalElements())).body(page.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCommentById(@PathVariable Long post_id, @PathVariable Long id){
        return ResponseEntity.ok(commentService.readComment(post_id, id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateComment(@PathVariable Long post_id, @PathVariable Long id, @RequestBody CommentDTO commentDTO) {
        return ResponseEntity.ok(commentService.updateComment(post_id, id, commentDTO));
    }

    @DeleteMapping("/{id}")
    public void removePost(@PathVariable Long post_id,@PathVariable Long id){
        commentService.deleteComment(post_id, id);
    }
}
