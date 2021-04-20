package com.softserveinc.ita.homeprojectblog.service.impl;

import com.softserveinc.ita.homeprojectblog.dto.UsersDto;
import com.softserveinc.ita.homeprojectblog.generated.model.User;
import com.softserveinc.ita.homeprojectblog.repository.UserRepository;
import com.softserveinc.ita.homeprojectblog.service.UserService;
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
    public UsersDto getAllUsers(BigDecimal id, String name, String sort, Integer pageNum, Integer pageSize) {
        // TODO find out for what is the ID for here
        Page<User> page;
        List<User> users;
        long quantity;

        if (name != null) {

            page = userRepository.findByName(name, PageRequest.of(pageNum, pageSize, getSorter(sort)));
            users = page.getContent();
            quantity = page.getTotalElements();

            return new UsersDto(users, quantity);
        }

        page = userRepository.findAll(PageRequest.of(pageNum, pageSize, getSorter(sort)));

        users = page.getContent();
        quantity = page.getTotalElements();

        return new UsersDto(users, quantity);
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
    public User getUserById(BigDecimal id) {
        User user = null;
        Optional<User> optional = userRepository.findById(id);
        if (optional.isPresent()) {
            user = optional.get();
        }
        return user;
    }

    @Override
    public User signUp(User body) {
        return userRepository.save(body);
    }

    @Override
    public void deleteUser(BigDecimal id) {
        userRepository.deleteById(id);
    }

    @Override
    public User updateUser(User body, BigDecimal id) {
        if (body.getId() == null) {
            body.setId(id);
        }

        return userRepository.save(body);
    }


}