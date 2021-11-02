package com.homeproject.blog.backend.business.services.impl;

import com.homeproject.blog.backend.business.converters.UserConverter;
import com.homeproject.blog.backend.business.exceptions.NotFoundException;
import com.homeproject.blog.backend.business.models.DTO.RoleTypeDTO;
import com.homeproject.blog.backend.business.models.DTO.UserDTO;
import com.homeproject.blog.backend.business.services.RoleService;
import com.homeproject.blog.backend.business.services.UserService;
import com.homeproject.blog.backend.database.repositories.UserRepository;
import com.homeproject.blog.backend.persistence.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private RoleService roleService;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public UserDTO save(UserDTO user) throws NotFoundException {
        UserEntity userEntity = userRepository.findUserByName(user.getName());
        if (userEntity != null) {
            throw new NotFoundException();
        }
        user.setRole(roleService.identify(user.getRole()));
        user.setPassword(encoder.encode(user.getPassword()));
        UserEntity entity = userConverter.userToEntity(user);
        return userConverter.entityToUser(userRepository.save(entity));
    }

    @Override
    public UserDTO findByUsername(String username) throws NotFoundException {
        UserEntity userEntity = userRepository.findUserByName(username);
        if (userEntity == null) {
            throw new NotFoundException();
        }
        return userConverter.entityToUser(userEntity);
    }

    @Override
    public UserDTO findById(Long id) throws NotFoundException {
        Optional<UserEntity> entity = userRepository.findById(id);
        if (entity.isEmpty()) {
            throw new NotFoundException();
        }
        return userConverter.entityToUser(entity.get());
    }

    @Override
    public Page<UserDTO> findAll(Long id, String name, Integer pageNum, Integer pageSize, String sort) {
        Sort sorting;
        if (sort != null) {
            if (sort.charAt(0) == '-') {
                sorting = Sort.by(sort.substring(1)).descending();
            } else {
                sorting = Sort.by(sort);
            }
        } else {
            sorting = Sort.by("id").descending();
        }
        pageNum = pageNum == null ? pageNum = 0 : pageNum;
        pageSize = pageSize == null ? pageSize = 10 : pageSize;
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize, sorting);
        Page<UserEntity> allByIdAndName = userRepository.findAllByIdAndName(pageRequest, id, name);
        Page<UserDTO> page = new PageImpl<>(allByIdAndName.stream().map(userConverter::entityToUser).collect(Collectors.toList()), pageRequest, allByIdAndName.getTotalElements());
        return page;
    }

    @Override
    public void deleteUser(Long id) {
        Optional<UserEntity> entity = userRepository.findById(id);
        if (entity.isEmpty()) {
            throw new NotFoundException();
        }
        userRepository.deleteById(id);
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO user) {
        UserDTO dto = findById(id);
        dto.setName(user.getName());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setPassword(encoder.encode(user.getPassword()));
        UserEntity entity = userRepository.save(userConverter.userToEntity(dto));
        return userConverter.entityToUser(entity);
    }

    @Override
    public RoleTypeDTO getUserRole(Long id) {
        UserDTO user = findById(id);
        return user.getRole();
    }

    @Override
    public RoleTypeDTO updateUserRole(Long id, RoleTypeDTO role) {
        UserDTO user = findById(id);
        user.setRole(roleService.identify(role));
        save(user);
        return role;
    }
}