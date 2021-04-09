package com.softserveinc.ita.homeprojectblog.repository.impl;

import com.softserveinc.ita.homeprojectblog.model.User;
import com.softserveinc.ita.homeprojectblog.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserRepositoryImpl implements UserRepository {

    ArrayList<User> users = new ArrayList<>();

    {
        users.add(new User(new BigDecimal("1"), "Tertey", "Vladyslav","Frolov","tertey7@gmail.com","227", User.RoleEnum.ADMIN));
        users.add(new User(new BigDecimal("2"), "sadfa", "Chan","Lee","sdfg@gmail.com","227", User.RoleEnum.USER));
        users.add(new User(new BigDecimal("3"), "xbdsfg", "Van","Chen","fgjh@gmail.com","227", User.RoleEnum.USER));
        users.add(new User(new BigDecimal("4"), "fgjh", "Suleyman","Mehdy","rtbnsd@gmail.com","227", User.RoleEnum.USER));
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }

    @Override
    public User getUserById(BigDecimal id) {
        return users.get(Integer.parseInt(String.valueOf(id))-1);
    }

    @Override
    public User setUser(User body) {
        users.add(body);
        return users.get(users.size()-1);
    }
}
