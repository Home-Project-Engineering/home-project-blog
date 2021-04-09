package com.softserveinc.ita.homeprojectblog.service;

import com.softserveinc.ita.homeprojectblog.model.User;

import java.math.BigDecimal;
import java.util.List;

public interface UserService {
    public List<User> getAllUsers();

    User getUserById(BigDecimal id);

    User setUser(User body);
}
