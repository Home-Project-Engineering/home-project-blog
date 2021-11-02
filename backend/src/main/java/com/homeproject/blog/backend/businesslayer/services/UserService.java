package com.homeproject.blog.backend.businesslayer.services;

import com.homeproject.blog.backend.businesslayer.dto.ChangePasswordDTO;
import com.homeproject.blog.backend.businesslayer.dto.RoleDTO;
import com.homeproject.blog.backend.businesslayer.dto.UserDTO;
import com.homeproject.blog.backend.data.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    UserDTO getUser(Long id);

    UserDTO createUser(UserDTO userDTO);

    UserDTO updateUser(UserDTO userDTO, Long id);

    void removeUser(Long id);

    UserDTO getCurrentUser();

    Page<UserDTO> getUsers(Long id, String name, Pageable pageRequest);

    UserDTO updateCurrentUser(UserDTO userDTO);

    RoleDTO getUserRole(Long id);

    void updateCurrentUserPassword(ChangePasswordDTO changePasswordDto);

    RoleDTO updateUserRole(Long id, RoleDTO roleDto);

    User getCurrentUserByUsername();

}
