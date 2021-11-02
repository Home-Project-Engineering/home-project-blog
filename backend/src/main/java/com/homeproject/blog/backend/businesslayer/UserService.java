package com.homeproject.blog.backend.businesslayer;

import com.homeproject.blog.backend.dtos.RoleType;
import com.homeproject.blog.backend.dtos.User;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {
    User save(User user);

    User findByUsername(String username) throws UsernameNotFoundException;

    User findById(Long id) throws UsernameNotFoundException;

    Page<User> findAll(Long id, String name, Integer pageNum, Integer pageSize, String sort);

    void deleteUser(Long id);

    User updateUser(Long id, User user);

    RoleType getUserRole(Long id);

    RoleType updateUserRole(Long id, RoleType role);
}
