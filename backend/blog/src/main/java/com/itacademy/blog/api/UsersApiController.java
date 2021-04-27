package com.itacademy.blog.api;

import com.itacademy.blog.api.mapper.CommentMapper;
import com.itacademy.blog.api.mapper.PostMapper;
import com.itacademy.blog.api.mapper.UserMapper;
import com.itacademy.blog.data.repository.CommentRepo;
import com.itacademy.blog.model.*;
import com.itacademy.blog.services.DTO.CommentDTO;
import com.itacademy.blog.services.DTO.PostDTO;
import com.itacademy.blog.services.DTO.RoleDTO;
import com.itacademy.blog.services.DTO.UserDTO;

import com.itacademy.blog.services.query.EntitySpecificationService;
import com.itacademy.blog.services.service.CommentService;
import com.itacademy.blog.services.service.PostService;
import com.itacademy.blog.services.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import javax.xml.bind.ValidationException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-04-22T13:11:30.172595800+03:00[Europe/Kiev]")
@Controller
@RequestMapping("${openapi.homeProjectBlogService.base-path:/api/1}")
public class UsersApiController implements UsersApi {

    private final PostService postService;
    private final NativeWebRequest request;
    private final UserService userService;
    private EntitySpecificationService entitySpecificationService;
    private final CommentService commentService;
    private final CommentRepo commentRepo;


    @Autowired
    public UsersApiController(PostService postService, EntitySpecificationService entitySpecificationService, NativeWebRequest request, UserService userService, CommentService commentService, CommentRepo commentRepo) {
        this.postService = postService;
        this.request = request;
        this.userService = userService;
        this.entitySpecificationService = entitySpecificationService;
        this.commentService = commentService;
        this.commentRepo = commentRepo;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override

    public ResponseEntity<User> createUser(@Valid User user) {
        UserDTO createUserDto = UserMapper.INSTANCE.convert(user);
        UserDTO readUserDto = userService.createUser(createUserDto);
        User returnUser = UserMapper.INSTANCE.convert(readUserDto);
        return new ResponseEntity<User>(returnUser, HttpStatus.CREATED);
    }


    @Override
    @PreAuthorize("hasAuthority('users')")
    public ResponseEntity<Void> removeUser(BigDecimal id) {
        Optional<UserDTO> optionalUserDTO = Optional.ofNullable(userService.deleteUser(id.longValue()));

        User returnUser = UserMapper.INSTANCE.convert(optionalUserDTO.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @Override
    @PreAuthorize("hasAuthority('users')")
    public ResponseEntity<List<User>> getUsers(@Valid BigDecimal id, @Valid String name, @Valid String sort, @Valid Integer pageNum, @Valid Integer pageSize) {
        Map<String, String> filterMap = new HashMap<>();
        if (id != null) {
            filterMap.put("id", id.toString());
        } else {
            filterMap.put("id", null);
        }
        filterMap.put("name", name);
        Page<UserDTO> users = userService.findUsers(Optional.ofNullable(pageNum).orElse(1)
                , Optional.ofNullable(pageSize).orElse(10), Optional.ofNullable(sort).orElse("-id")
                , entitySpecificationService.getSpecification(filterMap));
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("X-Total-Count", String.valueOf(users.getTotalElements()));
        return new ResponseEntity<List<User>>(UserMapper.INSTANCE.convert(users.toList()), responseHeaders, HttpStatus.OK);
    }

    @Override
    @PreAuthorize("hasAuthority('users')")
    public ResponseEntity<User> getUser(BigDecimal id) {
        UserDTO readUserDto = userService.getUserById(id.longValue());

        User returnUser = UserMapper.INSTANCE.convert(readUserDto);

        return new ResponseEntity<>(returnUser, HttpStatus.OK);
    }


    @Override
    @PreAuthorize("hasAuthority('users')")
    public ResponseEntity<User> updateUser(BigDecimal id, @Valid User user) {
        UserDTO updateUserDto = UserMapper.INSTANCE.convert(user);
        UserDTO readUserDto = userService.updateUser(id.longValue(), updateUserDto);
        User returnUser = UserMapper.INSTANCE.convert(readUserDto);

        return new ResponseEntity<>(returnUser, HttpStatus.OK);
    }

    @Override
    @PermitAll
    public ResponseEntity<Comment> getCommentByCurrentUser(BigDecimal id) {
        Map<String, String> filterMap = new HashMap<>();
        filterMap.put("id", id.toString());
        filterMap.put("user.id", userService.getCurrentUserEntity().getId().toString());
        List<CommentDTO> comments = commentService.findComments(1
                , 1, "id"
                , entitySpecificationService.getSpecification(filterMap));
        Comment commentToReturn = CommentMapper.INSTANCE.convert(comments.get(0));
        return new ResponseEntity<>(commentToReturn, HttpStatus.OK);
    }

    @Override
    @PermitAll
    public ResponseEntity<List<Comment>> getCommentsByCurrentUser(@Valid BigDecimal id, @Valid String sort, @Valid Integer pageNum, @Valid Integer pageSize) {
        Map<String, String> filterMap = new HashMap<>();
        if (id != null) {
            filterMap.put("id", id.toString());
        } else {
            filterMap.put("id", null);
        }
        filterMap.put("user.id", userService.getCurrentUserEntity().getId().toString());
        List<Comment> comments = commentService.findComments(Optional.ofNullable(pageNum).orElse(1)
                , Optional.ofNullable(pageSize).orElse(10), Optional.ofNullable(sort).orElse("-id")
                , entitySpecificationService.getSpecification(filterMap));
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @Override
    @PermitAll
    public ResponseEntity<User> getCurrentUser() {
        UserDTO userToReturn = userService.getCurrentUser();
        return new ResponseEntity<>(UserMapper.INSTANCE.convert(userToReturn), HttpStatus.OK);
    }

    @Override
    @PermitAll
    public ResponseEntity<Post> getPostByCurrentUser(BigDecimal id) {
        Map<String, String> filterMap = new HashMap<>();
        filterMap.put("id", id.toString());
        filterMap.put("user.id", userService.getCurrentUserEntity().getId().toString());
        List<PostDTO> posts = postService.findPosts(1
                , 1, "id"
                , entitySpecificationService.getSpecification(filterMap));
        Post postToReturn = PostMapper.INSTANCE.convert(posts.get(0));
        return new ResponseEntity<>(postToReturn, HttpStatus.OK);
    }

    @Override
    @PermitAll
    public ResponseEntity<List<Post>> getPostsByCurrentUser(@Valid BigDecimal id, @Valid String tagId, @Valid String tagName, @Valid String sort, @Valid Integer pageNum, @Valid Integer pageSize) {
        Map<String, String> filterMap = new HashMap<>();
        if (id != null) {
            filterMap.put("id", id.toString());
        } else {
            filterMap.put("id", null);
        }
        filterMap.put("tags.id", tagId);
        filterMap.put("tags.name", tagName);
        filterMap.put("user.id", userService.getCurrentUserEntity().getId().toString());
        List<Post> posts = postService.findPosts(Optional.ofNullable(pageNum).orElse(1)
                , Optional.ofNullable(pageSize).orElse(10), Optional.ofNullable(sort).orElse("-id")
                , entitySpecificationService.getSpecification(filterMap));
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @Override
    @PermitAll
    public ResponseEntity<Void> removeCommentByCurrentUser(BigDecimal id) {
        Long postId = commentRepo.findOneByUserIdAndId(userService.getCurrentUserEntity().getId(), id.longValue()).get().getPost().getId();
        if (getCommentByCurrentUser(id).getBody() == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        Optional<CommentDTO> optionalCommentDTO = Optional.ofNullable(commentService.deleteComment(postId.longValue(), id.longValue()));
        if (!optionalCommentDTO.isPresent()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    @PermitAll
    public ResponseEntity<Void> removePostByCurrentUser(BigDecimal id) {
        if (getPostByCurrentUser(id).getBody() == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        Optional<PostDTO> optionalPostDTO = Optional.ofNullable(postService.deletePost(id.longValue()));
        if (!optionalPostDTO.isPresent()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //return Comment not list
    @Override
    @PermitAll
    public ResponseEntity<Comment> updateCommentByCurrentUser(BigDecimal id, @Valid Comment comment) {
        Long post_id = commentRepo.findOneByUserIdAndId(userService.getCurrentUserEntity().getId(), id.longValue()).get().getPost().getId();
        if (getCommentByCurrentUser(id).getBody() != null) {
            return new ResponseEntity<>(CommentMapper.INSTANCE.convert(commentService.updateComment(post_id, id.longValue(), CommentMapper.INSTANCE.convert(comment))), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    @PermitAll
    public ResponseEntity<User> updateCurrentUser(@Valid User user) {
        return updateUser(getCurrentUser().getBody().getId(), user);
    }

    @Override
    @PermitAll
    public ResponseEntity<Post> updatePostByCurrentUser(BigDecimal id, @Valid Post post) {
        if (getPostByCurrentUser(id).getBody() != null) {
            return new ResponseEntity<>(PostMapper.INSTANCE.convert(postService.updatePost(id.longValue(), PostMapper.INSTANCE.convert(post))), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    @PreAuthorize("hasAuthority('role')")
    public ResponseEntity<Role> getUserRole(BigDecimal id) {
        RoleDTO role = userService.getUserRole(id.longValue());
        Role toReturn = UserMapper.INSTANCE.convert(role);
        return new ResponseEntity<>(toReturn, HttpStatus.OK);
    }

    @Override
    @PermitAll
    public ResponseEntity<Void> updateCurrentUserPassword(@Valid Password password) throws ValidationException {
        userService.updateCurrentUserPassword(password.getOldPassword(), password.getNewPassword());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    @PreAuthorize("hasAuthority('role')")
    public ResponseEntity<Role> updateUserRole(BigDecimal id, @Valid Role role) {
        RoleDTO updateRoleDto = UserMapper.INSTANCE.convert(role);
        RoleDTO readRoleDto = userService.updateUserRole(id.longValue(), updateRoleDto);
        Role returnRole = UserMapper.INSTANCE.convert(readRoleDto);
        return new ResponseEntity<>(returnRole, HttpStatus.OK);
    }
}
