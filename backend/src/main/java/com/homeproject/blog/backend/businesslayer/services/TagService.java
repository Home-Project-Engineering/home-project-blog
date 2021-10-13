package com.homeproject.blog.backend.businesslayer.services;

import com.homeproject.blog.backend.businesslayer.dto.TagDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface TagService {

    TagDTO getTag(Long id);

    Page<TagDTO> getTags(Long id, String name, PageRequest pageRequest);

    void deleteTag(Long id);
}
