package com.softserveinc.ita.homeprojectblog.controller;

import com.softserveinc.ita.homeprojectblog.dto.UsersDto;
import com.softserveinc.ita.homeprojectblog.exceptions.NoSuchUserException;
import com.softserveinc.ita.homeprojectblog.exceptions.NoSuchUsersException;
import com.softserveinc.ita.homeprojectblog.generated.api.UsersApi;
import com.softserveinc.ita.homeprojectblog.generated.model.User;
import com.softserveinc.ita.homeprojectblog.service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController implements UsersApi {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<Void> deleteUser(BigDecimal id) {
        userService.deleteUser(id);
        return null;
    }

    @Override
    public ResponseEntity<List<User>> getAllUsers(@Valid BigDecimal id, @Valid String name, @Valid String sort, @Valid Integer pageNum, @Valid Integer pageSize) {

        UsersDto usersAndSize = userService.getAllUsers(
                id,
                name,
                sort, // UserAPI set default
                Optional.ofNullable(pageNum).orElse(1),
                Optional.ofNullable(pageSize).orElse(10));

        Collection<User> allUsers = usersAndSize.getUsers();

        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(usersAndSize.getQuantity()));

        // TODO find out if this approach is correct
        if (usersAndSize.getQuantity() == 0 || allUsers == null) {
            throw new NoSuchUsersException("There are no users for your request");
        }

        return new ResponseEntity(allUsers, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<User> getUser(BigDecimal id) {
        User user = userService.getUserById(id);

        if (user == null) {
            throw new NoSuchUserException("There is no user with ID = " +
                    id + " in Database");
        }

        return new ResponseEntity(user, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<User> signUp(@Valid User body) {
        User user = userService.signUp(body);
        return new ResponseEntity(user, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<User> updateUser(@Valid User body, BigDecimal id) {
        User user = userService.updateUser(body, id);
        return new ResponseEntity(user, HttpStatus.OK);
    }
}
