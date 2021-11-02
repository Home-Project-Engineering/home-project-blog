package com.homeproject.blog.backend.business.services;

import com.homeproject.blog.backend.business.models.DTO.TagDTO;
import com.homeproject.blog.backend.persistence.entity.TagEntity;

import java.util.Collection;
import java.util.List;

public interface TagService {

    TagDTO readTag(Long id);

    Collection<TagDTO> getTags();

    void deleteTag(Long id);

    List<TagEntity> identifyTags(List<TagEntity> tags);
}
