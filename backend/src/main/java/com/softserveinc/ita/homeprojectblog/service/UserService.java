package com.softserveinc.ita.homeprojectblog.service;

import com.softserveinc.ita.homeprojectblog.dto.PasswordDto;
import com.softserveinc.ita.homeprojectblog.dto.RoleDto;
import com.softserveinc.ita.homeprojectblog.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.math.BigDecimal;

@Validated
public interface UserService {

    UserDto getUser(BigDecimal id);

    UserDto getUserByName(String username);

    UserDto createUser(UserDto bodyDto);

    UserDto updateUser(UserDto body, BigDecimal id);

    void removeUser(BigDecimal id);

    UserDto getCurrentUser();

    Page<UserDto> getUsers(BigDecimal id, String name, String sort, Integer pageNum, Integer pageSize);

    UserDto updateCurrentUser(UserDto userDto);

    RoleDto getUserRole(BigDecimal id);

    void updateCurrentUserPassword(@Valid PasswordDto passwordDto);

    RoleDto updateUserRole(BigDecimal id, RoleDto roleDto);
}
