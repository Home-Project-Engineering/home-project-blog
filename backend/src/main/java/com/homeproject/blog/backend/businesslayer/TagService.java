package com.homeproject.blog.backend.businesslayer;

import com.homeproject.blog.backend.entities.Tag;
import com.homeproject.blog.backend.exceptions.TagNotFoundException;

import java.util.Collection;

public interface TagService {

    Tag readTag(Long id) throws TagNotFoundException;

    Collection<Tag> getTags();

    void deleteTag(Long id) throws TagNotFoundException;
}
