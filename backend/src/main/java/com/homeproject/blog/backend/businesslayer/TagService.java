package com.homeproject.blog.backend.businesslayer;

import com.homeproject.blog.backend.data.entity.TagEntity;
import com.homeproject.blog.backend.dtos.Tag;
import com.homeproject.blog.backend.exceptions.ForbiddenActionException;
import com.homeproject.blog.backend.exceptions.TagNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Collection;
import java.util.List;

public interface TagService {

    Tag readTag(Long id) throws TagNotFoundException;

    Collection<Tag> getTags();

    void deleteTag(Long id) throws TagNotFoundException, ForbiddenActionException;

    List<TagEntity> identifyTags(List<Tag> tags);

    Page<Tag> findAll(Long id, String name, PageRequest pageRequest);
}
