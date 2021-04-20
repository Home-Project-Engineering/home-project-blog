package com.softserveinc.ita.home.home_project_blog.controllers;


import com.softserveinc.ita.home.home_project_blog.Error.ResponseError;
import com.softserveinc.ita.home.home_project_blog.dto.CreateUserDto;
import com.softserveinc.ita.home.home_project_blog.dto.UpdateUserDto;
import com.softserveinc.ita.home.home_project_blog.dto.UserDto;
import com.softserveinc.ita.home.home_project_blog.mappers.UserMapper;
import com.softserveinc.ita.home.home_project_blog.service.IUserService;
import com.softserveinc.ita.home.home_project_blog.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/api/0/users", produces = "application/json")
public class UsersController {
    private final IUserService userService;
    private final UserMapper mapper;

    @GetMapping(path = "/admin")
    public ResponseEntity<List<User>> getAllUsersWithPass(
            @RequestParam(defaultValue = "-id") String sort
    ) {
        return new ResponseEntity<>(userService.findAll(0, 100, sort).getContent(),HttpStatus.OK);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<UserDto>> getAllUsers(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "0") Integer page_num,
            @RequestParam(defaultValue = "50") Integer page_size,
            @RequestParam(defaultValue = "-id") String sort
    ) {
        Pageable paging = userService.pagination(page_num, page_size, sort);
        Page<User> pagedResult;
        if ((name != null) && (id != null)) {
            pagedResult = userService.getByNameAndId(name, id, paging);
        } else if (name != null) {
            pagedResult = userService.getByName(name, paging);
        } else if (id != null) {
            pagedResult = userService.getById(id, paging);
        } else {
            pagedResult = userService.findAll(paging);
        }
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("X-Total-Count",
                String.valueOf(pagedResult.getTotalElements()));

        List<UserDto> users = mapper.toUserDtos(pagedResult.getContent());
        return new ResponseEntity<>(users, responseHeaders, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long id) {
        Optional<User> user = userService.getById(id);
        return user.map(value -> new ResponseEntity<>(mapper.toUserDto(value),
                HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<User> signUp(@RequestBody CreateUserDto user) {
        return new ResponseEntity<>(userService.save(mapper.signUpToUser(user)), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<User> updateUser( @PathVariable Long id,
                                            @RequestBody UpdateUserDto user) {
        Optional<User> newUser = userService.update(id, mapper.UpdateToUser(user));
        return newUser.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

//    @PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
//    public ResponseEntity<User> updateUser( @PathVariable Long id,
//                                            @RequestBody CreateUserDto user) {
//        Optional<User> newUser = userService.update(id, mapper.signUpToUser(user));
//        return newUser.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
//                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//new ResponseError("404","User with id=\""+id+"\" hasn't been found."
//        if (Objects.isNull(userService.update(longId))) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

    @DeleteMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        if (userService.delete(id)) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    // @RequestParam(required = false, value = "id") Long id,
    //@RequestParam(required = false, value = "name") String name
//    ) {
        /*
        if (!StringUtils.isEmpty(id)) {
            ResponseEntity<User> resp = getUserById(id);
            if (resp.hasBody()) {
                List<User> users = new ArrayList<User>();
                users.add(resp.getBody());
                return new ResponseEntity<List<User>>(users, HttpStatus.OK);
            }
            return new ResponseEntity<List<User>>(resp.getStatusCode());
        }
        if (!StringUtils.isEmpty(name)) {
            List<User> usersByName = userService.getByName(name);
//            if (optUser.isPresent()) {
////            for (User user : users) {
////                if (user.getName().equalsIgnoreCase(name)) {
//                    List<User> users = new ArrayList<User>();
//                    users.add(optUser.get());
                    return new ResponseEntity<List<User>>( usersByName, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }*/
    //   var users = (List<User>) userService.findAll();
//        return new ResponseEntity<List<User>>(userService.findAll(), HttpStatus.OK);
//    }

}
