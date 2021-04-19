package com.itacademy.blog.services.service;

import com.itacademy.blog.data.entity.Tag;
import com.itacademy.blog.services.DTO.TagDTO;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface TagService {
    TagDTO deleteTag(Long id);

    TagDTO getTagById(Long id);

    List<TagDTO> findAllTags(Integer pageNumber, Integer pageSize, String sort, Specification<Tag> specification);
}
