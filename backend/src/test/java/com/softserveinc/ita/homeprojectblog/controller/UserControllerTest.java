package com.softserveinc.ita.homeprojectblog.controller;

import com.softserveinc.ita.homeprojectblog.generated.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.event.annotation.AfterTestMethod;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {

 /*   @Test
    void createUser() {
        User user;
        User testUser = new User();
        RestTemplate restTemplate = new RestTemplate();
        testUser.setName("John78new2111111");
        testUser.setFirstName("John");
        testUser.setLastName("Smith");
        testUser.setEmail("john.smith211111@example.com");
        testUser.setPassword("asdfasdf44444AAAAA");
        testUser.setRole(User.RoleEnum.ADMIN);

        user = restTemplate.postForObject("http://localhost:8080/api/1/users", testUser, User.class);
        testUser.setPassword("*****");
        assert user != null;
        BigDecimal id = user.getId();
        testUser.setId(id);

        restTemplate.delete("http://localhost:8080/api/1/users/" + user.getId());

        assertEquals(testUser, user);
    }

    @Test
    void getUser() {
        User tempUser = new User();
        RestTemplate restTemplate = new RestTemplate();
        tempUser.setName("John78new2111111");
        tempUser.setFirstName("John");
        tempUser.setLastName("Smith");
        tempUser.setEmail("john.smith211111@example.com");
        tempUser.setPassword("asdfasdf44444AAAAA");
        tempUser.setRole(User.RoleEnum.ADMIN);

        User user = restTemplate.postForObject("http://localhost:8080/api/1/users", tempUser, User.class);
        BigDecimal id = user.getId();

        User testUser = restTemplate.getForObject("http://localhost:8080/api/1/users/" + id, User.class);
        restTemplate.delete("http://localhost:8080/api/1/users/" + id);
        assertEquals(user, testUser);
    }*/
}