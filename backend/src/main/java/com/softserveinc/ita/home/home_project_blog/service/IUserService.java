package com.softserveinc.ita.home.home_project_blog.service;

import com.softserveinc.ita.home.home_project_blog.dto.CreateUserDto;
import com.softserveinc.ita.home.home_project_blog.dto.UserDto;
import com.softserveinc.ita.home.home_project_blog.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.Optional;

@Validated
public interface IUserService {
    public Pageable pagination(Integer pageNum, Integer pageSize, String sortBy);
    Page<User> findAll(Pageable paging);
    Page<User> findAll(Integer pageNum, Integer pageSize, String sortBy);
//    PageUserDto findAllDto(Integer pageNum, Integer pageSize, String sortBy);
    Page<User> getByName(String name, Integer pageNum, Integer pageSize, String sortBy);
    Page<User> getByName(String name, Pageable paging);
    Page<User> getById(Long id, Pageable paging);
    Page<User> getByNameAndId(String name, Long id, Pageable paging);
    User getById(Long id);
    User save(@Valid User user);
    User update(Long id, @Valid User user);
    void delete (Long id);
}
