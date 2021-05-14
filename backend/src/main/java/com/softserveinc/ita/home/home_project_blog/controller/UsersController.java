package com.softserveinc.ita.home.home_project_blog.controller;

import com.softserveinc.ita.home.home_project_blog.controller.dto.CreateUserDto;
import com.softserveinc.ita.home.home_project_blog.controller.dto.RoleDto;
import com.softserveinc.ita.home.home_project_blog.controller.dto.UpdateUserDto;
import com.softserveinc.ita.home.home_project_blog.controller.dto.ViewUserDto;
import com.softserveinc.ita.home.home_project_blog.controller.mapper.UserMapperController;
import com.softserveinc.ita.home.home_project_blog.service.GeneralService;
import com.softserveinc.ita.home.home_project_blog.service.IUserService;
import lombok.RequiredArgsConstructor;
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
@RequestMapping(path = "/api/1/users", produces = "application/json")
public class UsersController {
    private final IUserService userService;
    private final UserMapperController mapper;
    private final GeneralService<ViewUserDto> generalService;

    @PreAuthorize("hasAuthority('users')")
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<ViewUserDto>> getAllUsers(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "1") Integer page_num,
            @RequestParam(defaultValue = "50") Integer page_size,
            @RequestParam(defaultValue = "-id") String sort
    ) {
        return generalService.toResponseEntity(mapper.toPageViewUserDto(
                userService.findAll(id, name, page_num, page_size, sort)));
    }

    @PreAuthorize("hasAuthority('users')")
    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<ViewUserDto> getUserById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(mapper.toViewUserDto(userService.getById(id)), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<ViewUserDto> signUp(@Valid @RequestBody CreateUserDto user) {
        return new ResponseEntity<>(mapper.toViewUserDto(userService.save(mapper.signUpToUserDto(user))), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('users')")
    @PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ViewUserDto> updateUser(@PathVariable Long id,
                                                  @Valid @RequestBody UpdateUserDto user) {
        return new ResponseEntity<>(
                mapper.toViewUserDto(userService.update(id, mapper.UpdateToUserDto(user))),
                HttpStatus.OK
        );
    }

    @PreAuthorize("hasAuthority('users')")
    @DeleteMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<ViewUserDto> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasAuthority('users')")
    @GetMapping(path = "/{id}/role", produces = "application/json")
    public ResponseEntity<RoleDto> getUserRole(@PathVariable("id") Long id) {
        return new ResponseEntity<>(mapper.toRoleDto(userService.getRole(id)), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('users')")
    @PutMapping(path = "/{id}/role", consumes = "application/json", produces = "application/json")
    public ResponseEntity<RoleDto> updateRole(@PathVariable Long id,
                                              @Valid @RequestBody RoleDto role) {
        return new ResponseEntity<>(mapper.toRoleDto(userService.updateRole(id, mapper.toRole(role))), HttpStatus.OK);
    }
}
