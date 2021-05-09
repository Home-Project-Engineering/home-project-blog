package com.softserveinc.ita.homeprojectblog.service;

import com.softserveinc.ita.homeprojectblog.dto.UserDto;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;

public interface UserService {

    UserDto getUserById(BigDecimal id);

    UserDto getUserByName(String username);

    UserDto createUser(UserDto bodyDto);

    UserDto updateUser(UserDto body, BigDecimal id);

    void deleteUser(BigDecimal id);

    UserDto getCurrentUser();

    Page<UserDto> getUsers(BigDecimal id, String name, String sort, Integer pageNum, Integer pageSize);
}
