package com.softserveinc.ita.home.home_project_blog.service;

import com.softserveinc.ita.home.home_project_blog.models.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<User> findAll();
    Optional<User> getById(Long id);
    User save(User user);
    Optional<User> update(User user);
    boolean delete (Long id);
}
