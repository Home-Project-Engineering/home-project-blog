package com.softserveinc.ita.homeprojectblog.service;

import com.softserveinc.ita.homeprojectblog.dto.UserDtoGet;
import com.softserveinc.ita.homeprojectblog.dto.UserDtoSet;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.math.BigDecimal;

@Validated
public interface UserService {
    Page<UserDtoGet> getAllUsers(@Valid BigDecimal id, @Valid String name, @Valid String sort, @Valid Integer pageNum, @Valid Integer pageSize);

    UserDtoGet getUserById(BigDecimal id);

    UserDtoGet getUserByName(String username);

    UserDtoGet createUser(@Valid UserDtoSet bodyDto);

    UserDtoGet updateUser(UserDtoSet body, BigDecimal id);

    void deleteUser(BigDecimal id);
}
