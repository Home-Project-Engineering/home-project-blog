package com.homeproject.blog.backend.data.repository;

import com.homeproject.blog.backend.data.entity.TagEntity;
import com.homeproject.blog.backend.dtos.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<TagEntity, Long> {
    Page<TagEntity> findAllByIdAndName(Pageable pageRequest, Long id, String name);
}
