package com.softserveinc.ita.homeprojectblog.repository;

import com.softserveinc.ita.homeprojectblog.model.User;

import java.math.BigDecimal;
import java.util.List;

public interface UserRepository {
    List<User> getAllUsers();

    User getUserById(BigDecimal id);

    User setUser(User body);
}
