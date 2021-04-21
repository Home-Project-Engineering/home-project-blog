package com.softserveinc.ita.homeprojectblog.service;

import com.softserveinc.ita.homeprojectblog.dto.UserDto;
import org.springframework.data.domain.Page;

import javax.validation.Valid;
import java.math.BigDecimal;

public interface UserService {
    Page<UserDto> getAllUsers(@Valid BigDecimal id, @Valid String name, @Valid String sort, @Valid Integer pageNum, @Valid Integer pageSize);

    UserDto getUserById(BigDecimal id);

    UserDto signUp(UserDto bodyDto);

    UserDto updateUser(UserDto body, BigDecimal id);

    void deleteUser(BigDecimal id);
}
