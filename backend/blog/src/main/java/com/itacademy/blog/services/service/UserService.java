package com.itacademy.blog.services.service;

import com.itacademy.blog.data.entity.User;
import com.itacademy.blog.services.DTO.PostDTO;
import com.itacademy.blog.services.DTO.UserDTO;
import com.itacademy.blog.services.exception.AlreadyExistBlogException;
import com.itacademy.blog.services.exception.NotFoundBlogException;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface UserService {

    UserDTO createUser(UserDTO createUserDto) throws AlreadyExistBlogException;

    UserDTO updateUser(Long id, UserDTO updateUserDto) throws NotFoundBlogException;

    Page<UserDTO> findUsers(Integer pageNumber, Integer pageSize, String sort, Specification<User> specification);

    UserDTO getUserById(Long id) throws NotFoundBlogException;

    UserDTO deleteUser(Long id) throws NotFoundBlogException;

    UserDTO getCurrentUser();

    User getCurrentUserEntity();
}
