package com.itacademy.blog.api;

import com.itacademy.blog.model.Comment;
import com.itacademy.blog.model.Post;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-04-08T12:24:27.072387+03:00[Europe/Kiev]")
@Controller
@RequestMapping("${openapi.homeProjectBlogService.base-path:/api/1}")
public class PostsApiController implements PostsApi {
    @Override
    public ResponseEntity<Post> create(@Valid Post post) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteComment(BigDecimal postId, BigDecimal id) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deletePost(@PathVariable("post_id") BigDecimal id) {

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<List<Comment>> getAllComments(BigDecimal postId, @Valid BigDecimal id, @Valid String userName, @Valid String userId, @Valid String sort, @Valid Integer pageNum, @Valid Integer pageSize) {
        return null;
    }

    @Override
    public ResponseEntity<List<Post>> getAllPosts(@Valid BigDecimal id, @Valid String tagId, @Valid String tagName, @Valid String userId, @Valid String sort, @Valid Integer pageNum, @Valid Integer pageSize) {
        return null;
    }

    @Override
    public ResponseEntity<Comment> getCommentById(BigDecimal postId, BigDecimal id) {
        return null;
    }

    @Override
    public ResponseEntity<Post> getPost(BigDecimal id) {
        return null;
    }

    @Override
    public ResponseEntity<Comment> leaveAComment(BigDecimal postId, @Valid Comment comment) {
        return null;
    }

    @Override
    public ResponseEntity<Comment> updateComment(BigDecimal postId, BigDecimal id, @Valid Comment comment) {
        return null;
    }

    @Override
    public ResponseEntity<Post> updatePost(BigDecimal id, @Valid Post post) {
        return null;
    }

    private final NativeWebRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public PostsApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

}
