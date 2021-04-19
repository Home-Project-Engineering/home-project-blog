package com.softserveinc.ita.home.home_project_blog.service;

import com.softserveinc.ita.home.home_project_blog.dto.CreateUserDto;
import com.softserveinc.ita.home.home_project_blog.dto.PageUserDto;
import com.softserveinc.ita.home.home_project_blog.dto.UserDto;
import com.softserveinc.ita.home.home_project_blog.models.UpdateUser;
import com.softserveinc.ita.home.home_project_blog.models.User;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    Page<User> findAll(Integer pageNum, Integer pageSize, String sortBy);
//    PageUserDto findAllDto(Integer pageNum, Integer pageSize, String sortBy);
    Optional<UserDto> getById(Long id);
    User save(CreateUserDto user);
    Optional<UserDto> update(Long id, CreateUserDto user);
    boolean delete (Long id);
}
