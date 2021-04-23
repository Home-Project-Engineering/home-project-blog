package com.itacademy.blog.api;

import com.itacademy.blog.api.mapper.CommentMapper;
import com.itacademy.blog.api.mapper.PostMapper;
import com.itacademy.blog.api.mapper.UserMapper;
import com.itacademy.blog.data.repository.PostRepo;
import com.itacademy.blog.model.Comment;
import com.itacademy.blog.model.Post;
import com.itacademy.blog.services.DTO.CommentDTO;
import com.itacademy.blog.services.DTO.PostDTO;
import com.itacademy.blog.services.query.EntitySpecificationService;
import com.itacademy.blog.services.service.CommentService;
import com.itacademy.blog.services.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-04-08T12:24:27.072387+03:00[Europe/Kiev]")
@Controller
@RequestMapping("${openapi.homeProjectBlogService.base-path:/api/1}")
public class PostsApiController implements PostsApi {
    private final NativeWebRequest request;
    private final CommentService commentService;
    private final PostService postService;
    private final EntitySpecificationService entitySpecificationService;
    @Autowired
    PostRepo postRepo;

    @Autowired
    public PostsApiController(NativeWebRequest request, CommentService commentService, PostService postService, EntitySpecificationService entitySpecificationService) {
        this.request = request;
        this.commentService = commentService;
        this.postService = postService;
        this.entitySpecificationService = entitySpecificationService;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    @PermitAll
    public ResponseEntity<Post> createPost(@Valid Post post) {
        PostDTO createPostDto = PostMapper.INSTANCE.convert(post);
        PostDTO readPostDto = postService.createPost(createPostDto);

        Post returnPost = PostMapper.INSTANCE.convert(readPostDto);
        returnPost.setUser(UserMapper.INSTANCE.convert(readPostDto.getUser()));
        return new ResponseEntity<Post>(returnPost, HttpStatus.CREATED);
    }


    @Override
    @PreAuthorize("hasAuthority('posts:delete')")
    public ResponseEntity<Void> removePost(@PathVariable("id") BigDecimal id) {

        Optional<PostDTO> optionalPostDTO = Optional.ofNullable(postService.deletePost(id.longValue()));

        if (!optionalPostDTO.isPresent()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<List<Comment>> getComments(BigDecimal postId, @Valid BigDecimal id, @Valid String userName, @Valid String userId, @Valid String sort, @Valid Integer pageNum, @Valid Integer pageSize) {
        Map<String, String> filterMap = new HashMap<>();

        if (id != null) {
            filterMap.put("id", id.toString());
        } else {
            filterMap.put("id", null);
        }

        filterMap.put("post.id", postId.toString());
        filterMap.put("user.name", userName);
        filterMap.put("user.id", userId);



        List<Comment> comments = commentService.findComments(Optional.ofNullable(pageNum).orElse(1)
                , Optional.ofNullable(pageSize).orElse(10), Optional.ofNullable(sort).orElse("-id")
                , entitySpecificationService.getSpecification(filterMap));

        return comments.isEmpty() ? new ResponseEntity<>(HttpStatus.BAD_REQUEST) : new ResponseEntity<List<Comment>>(comments, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<List<Post>> getPosts(@Valid BigDecimal id, @Valid String tagId, @Valid String tagName, @Valid String userId, @Valid String sort, @Valid Integer pageNum, @Valid Integer pageSize) {
        Map<String, String> filterMap = new HashMap<>();

        if (id != null) {
            filterMap.put("id", id.toString());
        } else {
            filterMap.put("id", null);
        }

        filterMap.put("tags.id", tagId);
        //Problem with handling tags with spaces in their names
        //Have not started working on the solution yet
        filterMap.put("tags.name", tagName);
        filterMap.put("user.id", userId);


        List<Post> posts = postService.findPosts(Optional.ofNullable(pageNum).orElse(1)
                , Optional.ofNullable(pageSize).orElse(10), Optional.ofNullable(sort).orElse("-id")
                , entitySpecificationService.getSpecification(filterMap));

        return posts.isEmpty() ? new ResponseEntity<>(HttpStatus.BAD_REQUEST) : new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Comment> getComment(BigDecimal postId, BigDecimal id) {

        CommentDTO readCommentDto = commentService.getCommentById(postId.longValue(), id.longValue());

        Comment returnComment = CommentMapper.INSTANCE.convert(readCommentDto);

        return new ResponseEntity<>(returnComment, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Post> getPost(BigDecimal id) {
        PostDTO readPostDto = postService.getPostById(id.longValue());

        Post returnPost = PostMapper.INSTANCE.convert(readPostDto);

        return new ResponseEntity<>(returnPost, HttpStatus.OK);
    }

    @Override
    @PermitAll
    public ResponseEntity<Comment> createComment(BigDecimal postId, @Valid Comment comment) {
        CommentDTO createCommentDto = CommentMapper.INSTANCE.convert(comment);
        CommentDTO readCommentDto = commentService.createComment(postId.longValue(), createCommentDto);

        Comment returnComment = CommentMapper.INSTANCE.convert(readCommentDto);
        return new ResponseEntity<Comment>(returnComment, HttpStatus.CREATED);
    }

    //to do
    @Override
    @PreAuthorize("hasAuthority('coment:update')")
    public ResponseEntity<Comment> updateComment(BigDecimal postId, BigDecimal id, @Valid Comment comment) {
        return null;
    }

    @Override
    @PreAuthorize("hasAuthority('post:update')")
    public ResponseEntity<Post> updatePost(BigDecimal id, @Valid Post post) {
        PostDTO updatePostDto = PostMapper.INSTANCE.convert(post);
        PostDTO readPostDto = postService.updatePost(id.longValue(), updatePostDto);

        Post returnPost = PostMapper.INSTANCE.convert(readPostDto);

        return new ResponseEntity<>(returnPost, HttpStatus.OK);
    }



    @Override
    @PreAuthorize("hasAuthority('comment:remove')")
    public ResponseEntity<Void> removeComment(BigDecimal postId, BigDecimal id) {

        Optional<CommentDTO> optionalCommentDTO = Optional.ofNullable(commentService.deleteComment(postId.longValue(), id.longValue()));

        if (!optionalCommentDTO.isPresent()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
