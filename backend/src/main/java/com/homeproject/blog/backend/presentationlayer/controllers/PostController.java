package com.homeproject.blog.backend.presentationlayer.controllers;

import com.homeproject.blog.backend.businesslayer.CommentService;
import com.homeproject.blog.backend.businesslayer.PostService;
import com.homeproject.blog.backend.dtos.Comment;
import com.homeproject.blog.backend.dtos.Post;
import com.homeproject.blog.backend.presentationlayer.converters.CommentConverter;
import com.homeproject.blog.backend.presentationlayer.converters.PostConverter;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@Controller
public class PostController implements PostsApi {
    @Autowired
    private PostService postService;
    @Autowired
    private PostConverter postConverter;
    private final Logger LOG = LoggerFactory.getLogger(PostController.class);
    @Autowired
    private CommentService commentService;
    @Autowired
    private CommentConverter commentConverter;

    public ResponseEntity<List<com.homeproject.blog.backend.presentationlayer.model.Post>> getPosts(BigDecimal id, String tagId, String tagName, String authorName, String sort, Integer pageNum, Integer pageSize) {
        LOG.info("Get all posts request");
        Long postId = id == null ? null : id.longValue();
        Page<Post> page = postService.getPosts(postId, tagId, tagName, authorName, sort, pageNum, pageSize);
        List<com.homeproject.blog.backend.presentationlayer.model.Post> responseList = postConverter.dtosToViews(page.toList());
        return ResponseEntity.ok().header("X-Total-Count", String.valueOf(page.getTotalElements())).body(responseList);
    }

    public ResponseEntity<com.homeproject.blog.backend.presentationlayer.model.Post> createPost(com.homeproject.blog.backend.presentationlayer.model.Post post) {
        LOG.info("Create new post request");
        Post newPost = postService.createPost(postConverter.viewToDTO(post));
        return new ResponseEntity<>(postConverter.dtoToView(newPost), HttpStatus.CREATED);
    }

    public ResponseEntity<com.homeproject.blog.backend.presentationlayer.model.Post> getPost(BigDecimal id) {
        LOG.info("Get post by id request");
        Post post = postService.readPost(id.longValue());
        return new ResponseEntity<>(postConverter.dtoToView(post), HttpStatus.OK);
    }

    public ResponseEntity<com.homeproject.blog.backend.presentationlayer.model.Post> updatePost(BigDecimal id, com.homeproject.blog.backend.presentationlayer.model.Post post) {
        LOG.info("Update post request");
        Post newPost = postService.updatePost(id.longValue(), postConverter.viewToDTO(post));
        return new ResponseEntity<>(postConverter.dtoToView(newPost), HttpStatus.OK);
    }

    public ResponseEntity<Void> removePost(BigDecimal id) {
        LOG.info("Delete post request");
        postService.deletePost(id.longValue());
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<List<com.homeproject.blog.backend.presentationlayer.model.Comment>> getComments(BigDecimal postId, BigDecimal id, String authorName, String sort, Integer pageNum, Integer pageSize) {
        LOG.info("Get all comments request");
        Page<Comment> page = commentService.findAll(id.longValue(), authorName, pageNum, pageSize, sort);
        List<com.homeproject.blog.backend.presentationlayer.model.Comment> responseList = commentConverter.dtosToViews(page.toList());
        return ResponseEntity.ok().header("X-Total-Count", String.valueOf(page.getTotalElements())).body(responseList);
    }

    public ResponseEntity<com.homeproject.blog.backend.presentationlayer.model.Comment> createComment(@ApiParam(value = "",required=true) @PathVariable("post_id") BigDecimal postId, @ApiParam(value = "" ,required=true )  @Valid @RequestBody com.homeproject.blog.backend.presentationlayer.model.Comment comment) {
        LOG.info("Create comment request");
        Comment newComment = commentService.createComment(commentConverter.viewToDTO(comment));
        return new ResponseEntity<>(commentConverter.dtoToView(newComment), HttpStatus.CREATED);
    }

    public ResponseEntity<com.homeproject.blog.backend.presentationlayer.model.Comment> getComment(BigDecimal postId, BigDecimal id) {
        LOG.info("Get comment by id request");
        Comment comment = commentService.readComment(id.longValue());
        return new ResponseEntity<>(commentConverter.dtoToView(comment), HttpStatus.OK);
    }

    public ResponseEntity<com.homeproject.blog.backend.presentationlayer.model.Comment> updateComment(BigDecimal postId, BigDecimal id, com.homeproject.blog.backend.presentationlayer.model.Comment comment) {
        Comment updatedComment = commentService.updateComment(id.longValue(), commentConverter.viewToDTO(comment));
        return new ResponseEntity<>(commentConverter.dtoToView(updatedComment), HttpStatus.OK);
    }

    public ResponseEntity<Void> removeComment(BigDecimal postId, BigDecimal id) {
        commentService.deleteComment(id.longValue());
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
