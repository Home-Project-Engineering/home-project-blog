package com.softserveinc.ita.home.home_project_blog.service;

import com.softserveinc.ita.home.home_project_blog.models.UpdateUser;
import com.softserveinc.ita.home.home_project_blog.models.User;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    Page<User> findAll(Integer pageNum, Integer pageSize, String sortBy);
    Optional<User> getById(Long id);
    User save(User user);
    Optional<User> update(Long id, UpdateUser user);
    boolean delete (Long id);
}
