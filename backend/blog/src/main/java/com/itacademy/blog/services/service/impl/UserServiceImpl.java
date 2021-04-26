package com.itacademy.blog.services.service.impl;


import com.itacademy.blog.data.entity.User;
import com.itacademy.blog.data.repository.UserRepo;
import com.itacademy.blog.services.DTO.UserDTO;
import com.itacademy.blog.services.mapper.UserMapper;
import com.itacademy.blog.services.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDTO createUser(UserDTO createUserDto) {
        User entityToCreate = UserMapper.INSTANCE.convert(createUserDto);
        entityToCreate.setPassword(passwordEncoder.encode(createUserDto.getPassword()));
        entityToCreate.setRole(User.RoleEnum.BLOGGER);
        userRepo.save(entityToCreate);
        return UserMapper.INSTANCE.convert(entityToCreate);
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO updateUserDto) {

        Optional<User> optionalUser = userRepo.findById(id);
        if (optionalUser.isPresent()) {
            User fromDB = optionalUser.get();
            if (updateUserDto.getName() != null && !updateUserDto.getName().equals(fromDB.getName())) {
                fromDB.setName(updateUserDto.getName());
            }
            if (updateUserDto.getFirstName() != null && !updateUserDto.getFirstName().equals(fromDB.getFirstName())) {
                fromDB.setFirstName(updateUserDto.getFirstName());
            }

            if (updateUserDto.getLastName() != null && !updateUserDto.getLastName().equals(fromDB.getLastName())) {
                fromDB.setLastName(updateUserDto.getLastName());
            }

            if (updateUserDto.getEmail() != null && !updateUserDto.getEmail().equals(fromDB.getEmail())) {
                fromDB.setEmail(updateUserDto.getEmail());
            }

            if (updateUserDto.getPassword() != null && !updateUserDto.getPassword().equals(fromDB.getPassword())) {
                fromDB.setPassword(passwordEncoder.encode(updateUserDto.getPassword()));
            }
            userRepo.save(fromDB);
            return UserMapper.INSTANCE.convert(fromDB);

        } else {
            throw new EntityNotFoundException("User with id:" + id + " is not found");
        }
    }

    @Override
    public Page<UserDTO> findUsers(Integer pageNumber, Integer pageSize, String sort, Specification<User> specification) {
        Page<User> toReturn = userRepo.findAll(specification, PageRequest
                .of(pageNumber - 1, pageSize, getSort(sort)));

        return UserMapper.INSTANCE.convert(toReturn);
    }

    private Sort getSort(String sort) {
        StringBuilder str = new StringBuilder(sort);

        if (str.charAt(0) == '-') {
            str.deleteCharAt(0);
            return Sort.by(Sort.Direction.DESC, str.toString());
        }

        return Sort.by(Sort.Direction.ASC, str.toString());
    }

    @Override
    public UserDTO getUserById(Long id) {
        User toGet = userRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id:" + id + " is not found"));
        return UserMapper.INSTANCE.convert(toGet);
    }

    @Override
    public UserDTO deleteUser(Long id) {
        User toDelete = userRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("User with id:" + id + " is not found"));
        userRepo.deleteById(id);

        return UserMapper.INSTANCE.convert(toDelete);
    }

    public User getCurrentUserEntity() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email;
        if (principal instanceof UserDetails) {
            email = ((UserDetails) principal).getUsername();
        } else {
            email = principal.toString();
        }
        return userRepo.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("User is not found"));
    }

    @Override
    public UserDTO getCurrentUser() {
        return UserMapper.INSTANCE.convert(getCurrentUserEntity());
    }
}