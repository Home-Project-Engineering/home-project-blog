package com.softserveinc.ita.homeprojectblog.controller;

import com.softserveinc.ita.homeprojectblog.api.UsersApi;
import com.softserveinc.ita.homeprojectblog.model.User;
import com.softserveinc.ita.homeprojectblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
public class UserController implements UsersApi {

    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity<Void> deleteUser(BigDecimal id) {
        return null;
    }

    @Override
    public ResponseEntity<List<User>> getAllUsers(@Valid BigDecimal id, @Valid String name, @Valid String sort, @Valid Integer pageNum, @Valid Integer pageSize) {

        // !! add some code for other variety of query parameters for pagination → map <String><String> for headers

        return new ResponseEntity(userService.getAllUsers(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<User> getUser(BigDecimal id) {
        return new ResponseEntity(userService.getUserById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<User> signUp(@Valid User body) {

        userService.setUser(body); // rework
        int i = userService.getAllUsers().size(); // rework

        return new ResponseEntity(userService.getUserById(new BigDecimal(i)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<User> updateUser(@Valid User body, BigDecimal id) {
        return null;
    }
}
