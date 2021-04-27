package com.softserveinc.ita.home.home_project_blog.service;

import com.softserveinc.ita.home.home_project_blog.service.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Validated
public interface IUserService {
    Pageable pagination(Integer pageNum, Integer pageSize, String sortBy);

    Page<UserDto> findAll(Pageable paging);

    Page<UserDto> findAll(Integer pageNum, Integer pageSize, String sortBy);

    Page<UserDto> getByName(String name, Integer pageNum, Integer pageSize, String sortBy);

    Page<UserDto> getByName(String name, Pageable paging);

    Page<UserDto> getById(Long id, Pageable paging);

    Page<UserDto> getByNameAndId(String name, Long id, Pageable paging);

    UserDto getById(Long id);

    UserDto getCurrentUser();

    UserDto save(@Valid UserDto user);

    UserDto update(Long id, @Valid UserDto user);

    UserDto updateCurrentUser(@Valid UserDto user);

    void delete(Long id);
}
