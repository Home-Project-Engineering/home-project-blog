package com.itacademy.blog.api;

import com.itacademy.blog.api.mapper.UserMapper;
import com.itacademy.blog.model.User;
import com.itacademy.blog.services.DTO.UserDTO;
import com.itacademy.blog.services.query.EntitySpecificationService;
import com.itacademy.blog.services.service.UserService;
import lombok.RequiredArgsConstructor;
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
import java.util.Map;
import java.util.Optional;
import java.util.List;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-04-08T12:24:27.072387+03:00[Europe/Kiev]")
@Controller
@RequestMapping("${openapi.homeProjectBlogService.base-path:/api/1}")
@RequiredArgsConstructor
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
    public ResponseEntity<Void> deleteUser(BigDecimal id) {
        Optional<UserDTO> optionalUserDTO = null;

            optionalUserDTO = Optional.ofNullable(userService.deleteUser(id.longValue()));

        if (optionalUserDTO.isPresent()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        User returnUser = UserMapper.INSTANCE.convert(optionalUserDTO.get());
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }


    @Override
    public ResponseEntity<List<User>> getAllUsers(@Valid BigDecimal id, @Valid String name, @Valid String sort, @Valid Integer pageNum, @Valid Integer pageSize) {
        Map<String, String> filterMap = new HashMap<>();

        if (id != null) {
            filterMap.put("id", id.toString());
        } else {
            filterMap.put("id", null);
        }
        filterMap.put("name", name);

        Page<User> users = userService.findUsers(Optional.of(pageNum).orElse(1)
                , Optional.of(pageSize).orElse(10), Optional.of(sort).orElse("-id")
                , entitySpecificationService.getSpecification(filterMap));


        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("X-Total-Count",String.valueOf( users.getTotalPages()));
         return users.isEmpty() ? new ResponseEntity<>(HttpStatus.BAD_REQUEST) : new ResponseEntity<List<User>>(users.toList(), responseHeaders, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<User> getUser(BigDecimal id) {
        UserDTO readUserDto = null;
        readUserDto = userService.getUserById(id.longValue());

        User returnUser = UserMapper.INSTANCE.convert(readUserDto);

        return new ResponseEntity<>(returnUser, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<User> signUp(@Valid User user) {
        UserDTO createUserDto = UserMapper.INSTANCE.convert(user);
        UserDTO readUserDto = userService.createUser(createUserDto);

        User returnUser = UserMapper.INSTANCE.convert(readUserDto);
        return new ResponseEntity<User>(returnUser, HttpStatus.CREATED);
    }


    @Override
    public ResponseEntity<User> updateUser(BigDecimal id, @Valid User user) {
        UserDTO updateUserDto = UserMapper.INSTANCE.convert(user);
        UserDTO readUserDto = userService.updateUser(id.longValue(), updateUserDto);

        User returnUser = UserMapper.INSTANCE.convert(readUserDto);

        return new ResponseEntity<>(returnUser, HttpStatus.OK);
    }

}
