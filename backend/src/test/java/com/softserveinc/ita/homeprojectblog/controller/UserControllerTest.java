package com.softserveinc.ita.homeprojectblog.controller;

import com.softserveinc.ita.homeprojectblog.generated.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {

    @Test
    void getUser() {
        RestTemplate restTemplate = new RestTemplate();
        User user = restTemplate.getForObject("http://localhost:8080/api/1/users/3", User.class);
//        System.out.println(user);

        User testUser = new User();
        testUser.setId(new BigDecimal(3));
        testUser.setName("John78new2");
        testUser.setFirstName("John");
        testUser.setLastName("Smith");
        testUser.setEmail("john.smith2@example.com");
        testUser.setPassword("*****");
        testUser.setRole(User.RoleEnum.ADMIN);

        assertEquals(user, testUser);
    }


    @Test
    void createUser() {
        RestTemplate restTemplate = new RestTemplate();
        User testUser = new User();
        testUser.setName("John78new2111111");
        testUser.setFirstName("John");
        testUser.setLastName("Smith");
        testUser.setEmail("john.smith211111@example.com");
        testUser.setPassword("asdfasdf44444AAAAA");
        testUser.setRole(User.RoleEnum.ADMIN);

        User user = restTemplate.postForObject("http://localhost:8080/api/1/users", testUser, User.class);
        System.out.println(user);
        testUser.setPassword("*****");
        assert user != null;
        BigDecimal id = user.getId();
        testUser.setId(id);
        restTemplate.delete("http://localhost:8080/api/1/users/" + id);

        assertEquals(user, testUser);
    }
}