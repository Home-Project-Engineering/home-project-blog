package com.homeproject.blog.backend.business.services;

import com.homeproject.blog.backend.business.exceptions.NotFoundException;
import com.homeproject.blog.backend.business.models.DTO.RoleTypeDTO;
import com.homeproject.blog.backend.business.models.DTO.UserDTO;
import org.springframework.data.domain.Page;

public interface UserService {
    UserDTO save(UserDTO user) throws NotFoundException;

    UserDTO findByUsername(String username);

    UserDTO findById(Long id);

    Page<UserDTO> findAll(Long id, String name, Integer pageNum, Integer pageSize, String sort);

    void deleteUser(Long id);

    UserDTO updateUser(Long id, UserDTO user);

    RoleTypeDTO getUserRole(Long id);

    RoleTypeDTO updateUserRole(Long id, RoleTypeDTO role);
}
