package com.softserveinc.ita.home.home_project_blog.service;

import com.softserveinc.ita.home.home_project_blog.service.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Validated
public interface IUserService {
    Page<UserDto> findAll(Long id, String name, Integer pageNum, Integer pageSize, String sort);

    UserDto getById(Long id);

    UserDto save(@Valid UserDto user);

    UserDto update(@Valid UserDto oldUser, @Valid UserDto newUser);

    UserDto update(Long id, @Valid UserDto user);

    void delete(Long id);
}
