package com.softserveinc.ita.home.home_project_blog.controller;

import com.softserveinc.ita.home.home_project_blog.controller.dto.UpdateUserDto;
import com.softserveinc.ita.home.home_project_blog.controller.dto.CreateUserDto;
import com.softserveinc.ita.home.home_project_blog.controller.dto.ViewUserDto;
import com.softserveinc.ita.home.home_project_blog.controller.mapper.UserMapperController;
import com.softserveinc.ita.home.home_project_blog.service.IUserService;
import com.softserveinc.ita.home.home_project_blog.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/api/0/users", produces = "application/json")
public class UsersController {
    private final IUserService userService;
    private final UserMapperController mapper;

    @PreAuthorize("hasAuthority('users:read')")
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<ViewUserDto>> getAllUsers(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "0") Integer page_num,
            @RequestParam(defaultValue = "50") Integer page_size,
            @RequestParam(defaultValue = "-id") String sort
    ) {
        Pageable paging = userService.pagination(page_num, page_size, sort);
        Page<UserDto> pagedResult;
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

        List<ViewUserDto> users = mapper.toViewUserDtos(pagedResult.getContent());
        return new ResponseEntity<>(users, responseHeaders, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('users:read')")
    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<ViewUserDto> getUserById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(mapper.toViewUserDto(userService.getById(id)), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<UserDto> signUp(@Valid @RequestBody CreateUserDto user) {
        return new ResponseEntity<>(userService.save(mapper.signUpToUserDto(user)), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('users:write')")
    @PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id,
                                              @Valid @RequestBody UpdateUserDto user) {
        return new ResponseEntity<>(userService.update(id, mapper.UpdateToUserDto(user)), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('users:write')")
    @DeleteMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<UserDto> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping(path = "/admin")
    public ResponseEntity<List<UserDto>> getAllUsersWithPass(
            @Valid @RequestParam(defaultValue = "-id") String sort
    ) {
        return new ResponseEntity<>(userService.findAll(0, 100, sort).getContent(), HttpStatus.OK);
    }
}
