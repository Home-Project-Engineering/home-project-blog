package com.softserveinc.ita.home.blog.service;

import com.softserveinc.ita.home.blog.model.User;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@Api(value = "users")
public interface UsersService {


    User signUp(@Valid @RequestBody User body);


    Collection<User> getAllUsers(
//            @ApiParam(value = "Find by id") @Valid @RequestParam(value = "id", required = false) long id
//            , @ApiParam(value = "Find by name") @Valid @RequestParam(value = "name", required = false) String name
//            , @ApiParam(value = "In order to execute *asc*, you need to specify in the search *id* or *name* parameter. In order to complete the *desc* sorting must be specified in the query parameter *-id* ", allowableValues = "id, -id, name, -name", defaultValue = "-id") @Valid @RequestParam(value = "sort", required = false, defaultValue = "-id") String sort
//            , @ApiParam(value = "") @Valid @RequestParam(value = "page_num", required = false) Integer pageNum
//            , @ApiParam(value = "") @Valid @RequestParam(value = "page_size", required = false) Integer pageSize
    );




    User getUser(@ApiParam(value = "", required = true) @PathVariable("id") long id);






    User updateUser(@ApiParam(value = "some parameters", required = true) @Valid @RequestBody User body
            , @ApiParam(value = "", required = true) @PathVariable("id") long id);




    User deleteUser(@ApiParam(value = "", required = true) @PathVariable("id") long id);








}
