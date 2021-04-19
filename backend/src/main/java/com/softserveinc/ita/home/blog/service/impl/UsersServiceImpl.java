package com.softserveinc.ita.home.blog.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserveinc.ita.home.blog.model.User;
import com.softserveinc.ita.home.blog.service.UsersService;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-04-15T11:28:34.593463600+03:00[Europe/Helsinki]")
@Controller
public class UsersServiceImpl implements UsersService {

    private static final Logger log = LoggerFactory.getLogger(UsersServiceImpl.class);

//    private final ObjectMapper objectMapper;

    private static final Map<Long, User> CLIENT_REPOSITORY_MAP = new HashMap<>();

    private static final AtomicLong CLIENT_ID_HOLDER = new AtomicLong();

    private final HttpServletRequest request;

    @Autowired
    public UsersServiceImpl(ObjectMapper objectMapper, HttpServletRequest request) {
//        this.objectMapper = objectMapper;
        this.request = request;
    }


    public User signUp(@Valid @RequestBody User body) {
        try {
            body.setUserId(CLIENT_ID_HOLDER.incrementAndGet());
            return CLIENT_REPOSITORY_MAP.putIfAbsent(body.getUserId(), body);
        } catch (Exception e) {
            log.error("wrong responce application/json", e);
            return null;
        }
    }

    public List<User> getAllUsers() {
        try {
            List<User> values = new ArrayList<User>(CLIENT_REPOSITORY_MAP.values());
            return values;
        } catch (Exception e) {
            log.error("wrong responce application/json", e);
            return null;
        }
    }

    public User getUser(@ApiParam(value = "", required = true) @PathVariable("id") long id) {
        try {
            return CLIENT_REPOSITORY_MAP.get(id);
        } catch (Exception e) {
            log.error("wrong responce application/json", e);
            return null;
        }

    }


    public User updateUser(@ApiParam(value = "some parameters", required = true) @Valid @RequestBody User body
            , @ApiParam(value = "", required = true) @PathVariable("id") long id) {
        try {
            CLIENT_REPOSITORY_MAP.put(id, body);
            return CLIENT_REPOSITORY_MAP.get(id);
        } catch (Exception e) {
            log.error("wrong responce application/json", e);
            return null;
        }
    }

    public User deleteUser(@ApiParam(value = "", required = true) @PathVariable("id") long id) {
        try {
            User t = CLIENT_REPOSITORY_MAP.get(id);
            CLIENT_REPOSITORY_MAP.remove(id);
            return t;
        } catch (Exception e) {
            log.error("wrong responce application/json", e);
            return null;
        }
    }


}
