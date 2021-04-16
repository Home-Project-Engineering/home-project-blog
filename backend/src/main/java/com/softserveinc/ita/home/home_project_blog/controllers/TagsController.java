package com.softserveinc.ita.home.home_project_blog.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

//@RestController
//@RequestMapping("/api/0")
class TagsController {
    @GetMapping("/tags")
    public List<String> getTags(@RequestParam(value = "name") String name) {
        List<String> list = new ArrayList<>();
        list.add("tag1");
        list.add("tag2");
        list.add("tag3");
        list.add("tag4");
        return list;
    }
}