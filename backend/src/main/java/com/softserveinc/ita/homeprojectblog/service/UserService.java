package com.softserveinc.ita.homeprojectblog.service;

import com.softserveinc.ita.homeprojectblog.dto.UserDto;
import com.softserveinc.ita.homeprojectblog.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public interface UserService {

    Page<UserDto> findUsers(Integer pageNum,
                            Integer pageSize,
                            String sort,
                            Specification<UserEntity> specification);

    UserDto getUserById(BigDecimal id);

    UserDto getUserByName(String username);

    UserDto createUser(UserDto bodyDto);

    UserDto updateUser(UserDto body, BigDecimal id);

    void deleteUser(BigDecimal id);

    UserDto getCurrentUser();

    Page<UserDto> getUsers(BigDecimal id, String name, String sort, Integer pageNum, Integer pageSize);
}
