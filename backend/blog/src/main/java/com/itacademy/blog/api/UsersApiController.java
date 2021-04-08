package com.itacademy.blog.api;

import com.itacademy.blog.api.converters.Converter;
import com.itacademy.blog.model.User;
import com.itacademy.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-04-08T12:24:27.072387+03:00[Europe/Kiev]")
@Controller
@RequestMapping("${openapi.homeProjectBlogService.base-path:/api/1}")
public class UsersApiController implements UsersApi {

    private final NativeWebRequest request;
    private final Converter converter;
    private final UserService userService;


    @Autowired
    public UsersApiController(NativeWebRequest request, Converter converter, UserService userService) {
        this.request = request;
        this.converter = converter;
        this.userService = userService;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Void> deleteUser(BigDecimal id) {
        return null;
    }

    @Override
    public ResponseEntity<List<User>> getAllUsers(@Valid BigDecimal id, @Valid String name, @Valid String sort, @Min(1) @Valid Integer pageNum, @Min(0) @Max(10) @Valid Integer pageSize) {

        List<User> users= converter.convert( userService.getAllEntities(pageNum, pageSize, name, id.longValue(), sort));
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<User> getUser(BigDecimal id) {
        return null;
    }

    @Override
    public ResponseEntity<User> signUp(@Valid User user) {
        return null;
    }

    @Override
    public ResponseEntity<User> updateUser(BigDecimal id, @Valid User user) {
        return null;
    }
}
