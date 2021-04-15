package com.itacademy.blog.services.service.impl;


import com.itacademy.blog.data.Entity.User;
import com.itacademy.blog.data.repository.UserRepo;
import com.itacademy.blog.services.DTO.UserDTO;
import com.itacademy.blog.services.exception.AlreadyExistBlogException;
import com.itacademy.blog.services.exception.NotFoundBlogException;
import com.itacademy.blog.services.mapper.UserMapper;
import com.itacademy.blog.services.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;

    @Override
    public UserDTO createUser(UserDTO createUserDto) {
        if (userRepo.findByEmail(createUserDto.getEmail()).isPresent()) {
            throw new AlreadyExistBlogException("User with email" + createUserDto.getEmail() + " is already exists");
        } else {
            User entityToCreate = UserMapper.INSTANCE.convert(createUserDto);
            userRepo.save(entityToCreate);
            return UserMapper.INSTANCE.convert(entityToCreate);
        }
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO updateUserDto) {

        Optional<User> optionalUser = userRepo.findById(id);
        if (optionalUser.isPresent()) {
            User fromDB = optionalUser.get();
            if (updateUserDto.getName() != null) {
                fromDB.setName(updateUserDto.getName());
            }
            if (updateUserDto.getFirstName() != null) {
                fromDB.setFirstName(updateUserDto.getFirstName());
            }

            if (updateUserDto.getLastName() != null) {
                fromDB.setLastName(updateUserDto.getLastName());
            }

            if (updateUserDto.getEmail() != null) {
                fromDB.setEmail(updateUserDto.getEmail());
            }

            if (updateUserDto.getPassword() != null) {
                fromDB.setPassword(updateUserDto.getPassword());
            }
            userRepo.save(fromDB);
            return UserMapper.INSTANCE.convert(fromDB);

        } else {
            throw new EntityNotFoundException("User with id:" + id + " is not found");
        }
    }

    @Override   
    public List<UserDTO> findUsers(Integer pageNumber, Integer pageSize, String sort, Specification<User> specification) {
        List<User> toReturn = userRepo.findAll(specification, PageRequest
                .of(pageNumber - 1, pageSize, getSort(sort))).toList();

        return UserMapper.INSTANCE.convert(toReturn);
    }

    private Sort getSort(String sort) {
         StringBuilder str = new StringBuilder(sort);

        if(str.charAt(0) == '-'){
            str.deleteCharAt(0);
            return Sort.by(Sort.Direction.DESC, str.toString());}

        return Sort.by(Sort.Direction.ASC, str.toString());
    }

    @Override
    public UserDTO getUserById(Long id){
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
}
