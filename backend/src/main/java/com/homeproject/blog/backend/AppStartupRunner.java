package com.homeproject.blog.backend;

import com.homeproject.blog.backend.data.entities.*;
import com.homeproject.blog.backend.data.repositories.RoleRepository;
import com.homeproject.blog.backend.data.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;


import static com.homeproject.blog.backend.data.entities.Role.RoleName.*;


//@Component
public class AppStartupRunner implements ApplicationRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        User user = new User();
        user.setName("admin");
        user.setFirstName("Ivan");
        user.setLastName("Nefedov");
        user.setEmail("email@test.com");
        user.setPassword("$2a$08$Ttc8LV3u6IfDq.dRyxe9ZuXfjcC1ngfjjgF5YVPCondb7RX4/KIGC");
        user.setRole(roleRepository.getRoleByName(ADMIN));
        userRepository.save(user);
    }
}
