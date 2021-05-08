package com.softserveinc.ita.homeprojectblog.service.impl;

import com.softserveinc.ita.homeprojectblog.dto.UserDto;
import com.softserveinc.ita.homeprojectblog.entity.RoleEntity;
import com.softserveinc.ita.homeprojectblog.entity.UserEntity;
import com.softserveinc.ita.homeprojectblog.repository.RoleRepository;
import com.softserveinc.ita.homeprojectblog.repository.UserRepository;
import com.softserveinc.ita.homeprojectblog.service.UserService;
import com.softserveinc.ita.homeprojectblog.mapper.UserMapperService;
import com.softserveinc.ita.homeprojectblog.util.page.Sorter;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.*;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    RoleRepository roleRepository;

    UserMapperService userMapperService;

    PasswordEncoder passwordEncoder;

    Sorter sorter;

    @Override
    public Page<UserDto> findUsers(Integer pageNum, Integer pageSize, String sort, Specification<UserEntity> specification) {
        pageNum--;
        Page<UserEntity> pageEntities = userRepository.findAll(specification, PageRequest
                .of(pageNum, pageSize, sorter.getSorter(sort)));

        return userMapperService.toUserDtoGetPage(pageEntities);
    }



    @Override
    public UserDto getUserById(BigDecimal id) {
        UserEntity userEntity = null;
        Optional<UserEntity> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            userEntity = optional.get();
        }
        return userMapperService.toUserDto(userEntity);
    }

    @Override
    public UserDto getUserByName(String username) {
        var currentUserEntity = userRepository.findByName(username).orElseThrow(() ->
                new UsernameNotFoundException("User does not exists"));
        return userMapperService.toUserDto(currentUserEntity);
    }

    @Override
    public UserDto createUser(UserDto userDto) {

        var userEntity = userMapperService.toUserEntity(userDto);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));

        Optional<RoleEntity> roleEntity = roleRepository.findByName(RoleEntity.NameEnum.BLOGGER);
        if(roleEntity.isPresent()) {
            userEntity.setRole(roleEntity.get());
        }else {
            userEntity.setRole(new RoleEntity());
        }

        userEntity.setCreateOn(OffsetDateTime.now());

        userRepository.save(userEntity);
        return userMapperService.toUserDto(userEntity);
    }

    @Override
    public UserDto updateUser(UserDto bodyDto, BigDecimal id) {
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

    @Override
    public UserDto getCurrentUser() {
        var principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        return getUserByName(username);
    }
}