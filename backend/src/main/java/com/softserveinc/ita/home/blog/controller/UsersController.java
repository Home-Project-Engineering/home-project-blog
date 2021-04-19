package com.softserveinc.ita.home.blog.controller;

import com.softserveinc.ita.home.blog.model.Error;
import com.softserveinc.ita.home.blog.model.User;
import com.softserveinc.ita.home.blog.service.UsersService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;


@RestController
public class UsersController {

    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }




    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "OK", response = User.class),
            @ApiResponse(code = 400, message = "The payload contains an error", response = Error.class),
            @ApiResponse(code = 200, message = "The unknown error appeard. Check your payload or contact support.", response = Error.class)})
    @RequestMapping(value = "/users",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.POST)
    @PostMapping(value = "/users")
    public ResponseEntity<?> signUp(@RequestBody User user) {
        usersService.signUp(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = User.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "The payload contains an error", response = Error.class),
            @ApiResponse(code = 200, message = "The unknown error appeard. Check your payload or contact support.", response = Error.class)})
    @RequestMapping(value = "/users",
            produces = {"application/json"},
            method = RequestMethod.GET)
    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> getAllUsers() {
        final List<User> users = (List<User>) usersService.getAllUsers();

        return users != null
                ? new ResponseEntity<>(users,HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



    @ApiOperation(value = "Get user by ID", nickname = "getUser", notes = "Returns a user", response = User.class, authorizations = {
            @Authorization(value = "basicAuth")}, tags = {"users",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = User.class),
            @ApiResponse(code = 400, message = "The payload contains an error", response = Error.class),
            @ApiResponse(code = 404, message = "The specified resource was not found", response = Error.class),
            @ApiResponse(code = 200, message = "The unknown error appeard. Check your payload or contact support.", response = Error.class)})
    @RequestMapping(value = "/users/{id}",
            produces = {"application/json"},
            method = RequestMethod.GET)
   @GetMapping(value = "/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable(name = "id") long id) {
        final User user = usersService.getUser(id);

        return user != null
                ? new ResponseEntity<>(user,HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }




    @ApiOperation(value = "Update user by ID", nickname = "updateUser", notes = "Updates a user", response = User.class, authorizations = {
            @Authorization(value = "basicAuth")}, tags = {"users",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = User.class),
            @ApiResponse(code = 400, message = "The payload contains an error", response = Error.class),
            @ApiResponse(code = 404, message = "The specified resource was not found", response = Error.class),
            @ApiResponse(code = 200, message = "The unknown error appeard. Check your payload or contact support.", response = Error.class)})
    @RequestMapping(value = "/users/{id}",
            produces = {"application/json"},
            consumes = {"application/json"},
            method = RequestMethod.PUT)
    @PutMapping(value = "/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable(name = "id") long id, @RequestBody User user) {
        final User t = usersService.updateUser(user, id);
        return t != null
                ? new ResponseEntity<>(t, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }


    @ApiOperation(value = "Delete user by ID", nickname = "deleteUser", notes = "Delete a user", authorizations = {
            @Authorization(value = "basicAuth")}, tags = {"users",})
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "The request was succesfully processed."),
            @ApiResponse(code = 400, message = "The payload contains an error", response = Error.class),
            @ApiResponse(code = 404, message = "The specified resource was not found", response = Error.class),
            @ApiResponse(code = 200, message = "The unknown error appeard. Check your payload or contact support.", response = Error.class)})
    @RequestMapping(value = "/users/{id}",
            produces = {"application/json"},
            method = RequestMethod.DELETE)
    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(name = "id") long id) {


        return (usersService.deleteUser(id) != null)
                ? new ResponseEntity<>(HttpStatus.GONE)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
