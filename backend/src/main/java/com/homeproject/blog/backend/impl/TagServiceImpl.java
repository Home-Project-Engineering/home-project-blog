package com.homeproject.blog.backend.impl;

import com.homeproject.blog.backend.classes.Tag;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TagServiceImpl {
    private static final Set<Tag> tags = new HashSet<>();
    private static final AtomicLong index = new AtomicLong();
    private static Long getNextIndex (){
        return index.getAndIncrement();}

}
