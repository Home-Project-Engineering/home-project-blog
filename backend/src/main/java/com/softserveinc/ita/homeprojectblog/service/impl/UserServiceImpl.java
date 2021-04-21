package com.softserveinc.ita.homeprojectblog.service.impl;

import com.softserveinc.ita.homeprojectblog.dto.UserDto;
import com.softserveinc.ita.homeprojectblog.entity.UserEntity;
import com.softserveinc.ita.homeprojectblog.repository.UserRepository;
import com.softserveinc.ita.homeprojectblog.service.UserService;
import com.softserveinc.ita.homeprojectblog.service.mapper.UserMapperService;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Page<UserDto> getAllUsers(BigDecimal id, String name, String sort, Integer pageNum, Integer pageSize) {
        pageNum--;
        // TODO find out for what is the ID for here
        Page<UserEntity> pageEntities;

        if (name != null) {
            pageEntities = userRepository.findByName(name, PageRequest.of(pageNum, pageSize, getSorter(sort)));
            return UserMapperService.INSTANCE.toUserDtoPage(pageEntities);
        }

        pageEntities = userRepository.findAll(PageRequest.of(pageNum, pageSize, getSorter(sort)));

        return UserMapperService.INSTANCE.toUserDtoPage(pageEntities);
    }

    private Sort getSorter(String sort) {
        StringBuilder str = new StringBuilder(sort);

        if (str.charAt(0) == '-') {
            str.deleteCharAt(0);
            return Sort.by(Sort.Direction.DESC, str.toString());
        }

        return Sort.by(Sort.Direction.ASC, str.toString());
    }

    @Override
    public UserDto getUserById(BigDecimal id) {
        UserEntity userEntity = null;
        Optional<UserEntity> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            userEntity = optional.get();
        }
        return UserMapperService.INSTANCE.toUserDto(userEntity);
    }

    @Override
    public UserDto signUp(UserDto bodyDto) {
        UserEntity userEntity = UserMapperService.INSTANCE.toUserEntity(bodyDto);
        userRepository.save(userEntity);
        return UserMapperService.INSTANCE.toUserDto(userEntity);
    }

    @Override
    public void deleteUser(BigDecimal id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDto updateUser(UserDto bodyDto, BigDecimal id) {
        if (bodyDto.getId() == null) {
            bodyDto.setId(id);
        }
        UserEntity bodyEntity = UserMapperService.INSTANCE.toUserEntity(bodyDto);
        bodyEntity = userRepository.save(bodyEntity);

        return UserMapperService.INSTANCE.toUserDto(bodyEntity);
    }


}