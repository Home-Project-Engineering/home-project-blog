package com.homeproject.blog.backend.services;

import com.homeproject.blog.backend.classes.Tag;

import java.util.Collection;

public interface TagService {
    Tag createTag(Tag tag);

    Tag updateTag(Long postId);

    Tag readTag(Long id);

    Collection<Tag> getTags();

    void deleteTag(Long id);
}
