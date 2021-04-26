package com.itacademy.blog.services.service;

import com.itacademy.blog.data.entity.User;
import com.itacademy.blog.services.DTO.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

public interface UserService {

    UserDTO createUser(UserDTO createUserDto);

    UserDTO updateUser(Long id, UserDTO updateUserDto);

    Page<UserDTO> findUsers(Integer pageNumber, Integer pageSize, String sort, Specification<User> specification);

    UserDTO getUserById(Long id);

    UserDTO deleteUser(Long id);

    UserDTO getCurrentUser();

    User getCurrentUserEntity();
}
