package com.softserveinc.ita.homeprojectblog.controller;

import com.softserveinc.ita.homeprojectblog.controller.mapper.UserMapperController;
import com.softserveinc.ita.homeprojectblog.dto.UserDto;
import com.softserveinc.ita.homeprojectblog.exceptions.NoSuchUserException;
import com.softserveinc.ita.homeprojectblog.exceptions.NoSuchUsersException;
import com.softserveinc.ita.homeprojectblog.generated.api.UsersApi;
import com.softserveinc.ita.homeprojectblog.generated.model.User;
import com.softserveinc.ita.homeprojectblog.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/1")
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

        Page<UserDto> userDtoPage = userService.getAllUsers(
                id,
                name,
                sort, // UserAPI set default
                Optional.ofNullable(pageNum).orElse(1),
                Optional.ofNullable(pageSize).orElse(10));

        Page<User> userPage = UserMapperController.INSTANCE.toUserPage(userDtoPage);

        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(userPage.getTotalElements()));

        // TODO find out if this approach is correct
        if (userPage.getTotalElements() == 0) {
            throw new NoSuchUsersException("There are no users for your request");
        }

        return new ResponseEntity(userPage.getContent(), headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<User> getUser(BigDecimal id) {
        UserDto userDto = userService.getUserById(id);

        if (userDto == null) {
            throw new NoSuchUserException("There is no user with ID = " +
                    id + " in Database");
        }

        return new ResponseEntity(UserMapperController.INSTANCE.toUser(userDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<User> signUp(@Valid User body) {
        UserDto userDto = UserMapperController.INSTANCE.toUserDto(body);
        userDto = userService.signUp(userDto);
        return new ResponseEntity(UserMapperController.INSTANCE.toUser(userDto), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<User> updateUser(@Valid User body, BigDecimal id) {
        UserDto userDto = UserMapperController.INSTANCE.toUserDto(body);
        userDto = userService.updateUser(userDto, id);

        return new ResponseEntity(UserMapperController.INSTANCE.toUser(userDto), HttpStatus.OK);
    }
}
