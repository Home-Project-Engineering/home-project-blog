package com.itacademy.blog.api;

import com.itacademy.blog.api.mapper.UserMapper;
import com.itacademy.blog.model.Comment;
import com.itacademy.blog.model.Post;
import com.itacademy.blog.model.User;
import com.itacademy.blog.services.DTO.UserDTO;
import com.itacademy.blog.services.query.EntitySpecificationService;
import com.itacademy.blog.services.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-04-22T13:11:30.172595800+03:00[Europe/Kiev]")
@Controller
@RequestMapping("${openapi.homeProjectBlogService.base-path:/api/1}")
public class UsersApiController implements UsersApi {

    private final NativeWebRequest request;
    private final UserService userService;
    private EntitySpecificationService entitySpecificationService;


    @Autowired
    public UsersApiController(EntitySpecificationService entitySpecificationService, NativeWebRequest request, UserService userService) {
        this.request = request;
        this.userService = userService;
        this.entitySpecificationService = entitySpecificationService;
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
    public ResponseEntity<Void> removeUser(BigDecimal id) {
        Optional<UserDTO> optionalUserDTO = null;

        optionalUserDTO = Optional.ofNullable(userService.deleteUser(id.longValue()));

        if (optionalUserDTO.isPresent()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        User returnUser = UserMapper.INSTANCE.convert(optionalUserDTO.get());
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }


    @Override
    public ResponseEntity<List<User>> getUsers(@Valid BigDecimal id, @Valid String name, @Valid String sort, @Valid Integer pageNum, @Valid Integer pageSize) {
        Map<String, String> filterMap = new HashMap<>();

        if (id != null) {
            filterMap.put("id", id.toString());
        } else {
            filterMap.put("id", null);
        }
        filterMap.put("name", name);

        Page<User> users = userService.findUsers(Optional.ofNullable(pageNum).orElse(1)
                , Optional.ofNullable(pageSize).orElse(10), Optional.ofNullable(sort).orElse("-id")
                , entitySpecificationService.getSpecification(filterMap));


        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("X-Total-Count",String.valueOf( users.getTotalElements()));
        return users.isEmpty() ? new ResponseEntity<>(HttpStatus.BAD_REQUEST) : new ResponseEntity<List<User>>(users.toList(), responseHeaders, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<User> getUser(BigDecimal id) {
        UserDTO readUserDto = null;
        readUserDto = userService.getUserById(id.longValue());

        User returnUser = UserMapper.INSTANCE. convert(readUserDto);

        return new ResponseEntity<>(returnUser, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<User> updateUser(BigDecimal id, @Valid User user) {
        UserDTO updateUserDto = UserMapper.INSTANCE.convert(user);
        UserDTO readUserDto = userService.updateUser(id.longValue(), updateUserDto);

        User returnUser = UserMapper.INSTANCE.convert(readUserDto);

        return new ResponseEntity<>(returnUser, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Comment> getCommentByCurrentUser(BigDecimal id) {
        return null;
    }

    @Override
    public ResponseEntity<List<Comment>> getCommentsByCurrentUser(@Valid BigDecimal id, @Valid String sort, @Valid Integer pageNum, @Valid Integer pageSize) {
        return null;
    }

    @Override
    public ResponseEntity<User> getCurrentUser() {
        return null;
    }

    @Override
    public ResponseEntity<Post> getPostByCurrentUser(BigDecimal id) {
        return null;
    }

    @Override
    public ResponseEntity<List<Post>> getPostsByCurrentUser(@Valid BigDecimal id, @Valid String tagId, @Valid String tagName, @Valid String sort, @Valid Integer pageNum, @Valid Integer pageSize) {
        return null;
    }

    @Override
    public ResponseEntity<Void> removeCommentByCurrentUser(BigDecimal id) {
        return null;
    }

    @Override
    public ResponseEntity<Void> removePostByCurrentUser(BigDecimal id) {
        return null;
    }
    @Override
    public ResponseEntity<List<Comment>> updateCommentByCurrentUser(BigDecimal id, @Valid Comment comment) {
        return null;
    }

    @Override
    public ResponseEntity<User> updateCurrentUser(@Valid User user) {
        return null;
    }

    @Override
    public ResponseEntity<Post> updatePostByCurrentUser(BigDecimal id, @Valid Post post) {
        return null;
    }


}
