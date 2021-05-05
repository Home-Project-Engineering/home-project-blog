package com.softserveinc.ita.homeprojectblog.service.impl;

import com.softserveinc.ita.homeprojectblog.dto.UserDtoGet;
import com.softserveinc.ita.homeprojectblog.dto.UserDtoSet;
import com.softserveinc.ita.homeprojectblog.entity.RoleEntity;
import com.softserveinc.ita.homeprojectblog.entity.UserEntity;
import com.softserveinc.ita.homeprojectblog.repository.RoleRepository;
import com.softserveinc.ita.homeprojectblog.repository.UserRepository;
import com.softserveinc.ita.homeprojectblog.service.UserService;
import com.softserveinc.ita.homeprojectblog.mapper.UserMapperService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    RoleRepository roleRepository;

    UserMapperService userMapperService;

    PasswordEncoder passwordEncoder;

    @Override
    public Page<UserDtoGet> getAllUsers(BigDecimal id, String name, String sort, Integer pageNum, Integer pageSize) {
        pageNum--;
        Page<UserEntity> pageEntities;

        if (name != null) {
            pageEntities = userRepository.findByName(name, PageRequest.of(pageNum, pageSize, getSorter(sort)));
            return userMapperService.toUserDtoGetPage(pageEntities);
        }

        pageEntities = userRepository.findAll(PageRequest.of(pageNum, pageSize, getSorter(sort)));

        return userMapperService.toUserDtoGetPage(pageEntities);
    }

    private Sort getSorter(String sort) {
        var str = new StringBuilder(sort);

        if (str.charAt(0) == '-') {
            str.deleteCharAt(0);
            return Sort.by(Sort.Direction.DESC, str.toString());
        }

        return Sort.by(Sort.Direction.ASC, str.toString());
    }

    @Override
    public UserDtoGet getUserById(BigDecimal id) {
        UserEntity userEntity = null;
        Optional<UserEntity> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            userEntity = optional.get();
        }
        return userMapperService.toUserDto(userEntity);
    }

    @Override
    public UserDtoGet getUserByName(String username) {
        var currentUserEntity = userRepository.findByName(username).orElseThrow(() ->
                new UsernameNotFoundException("User does not exists"));
        return userMapperService.toUserDto(currentUserEntity);
    }

    @Override
    public UserDtoGet createUser(UserDtoSet userDtoSet) {
        var userEntity = userMapperService.toUserEntity(userDtoSet);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        Optional<RoleEntity> roleEntity = roleRepository.findByName(RoleEntity.NameEnum.BLOGGER);
        if(roleEntity.isPresent()) {
            userEntity.setRole(roleEntity.get());
        }else {
            userEntity.setRole(new RoleEntity());
        }
        userRepository.save(userEntity);
        return userMapperService.toUserDto(userEntity);
    }

    @Override
    public UserDtoGet updateUser(UserDtoSet bodyDto, BigDecimal id) {
        if (bodyDto.getId() == null)
            bodyDto.setId(id);

        if (bodyDto.getPassword() == null) // update without password
            bodyDto.setPassword(getUserById(id).getPassword());


        var bodyEntity = userMapperService.toUserEntity(bodyDto);
        bodyEntity = userRepository.save(bodyEntity);

        return userMapperService.toUserDto(bodyEntity);
    }

    @Override
    public void deleteUser(BigDecimal id) {
        userRepository.deleteById(id);
    }
}