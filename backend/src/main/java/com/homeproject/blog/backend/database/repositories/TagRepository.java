package com.homeproject.blog.backend.database.repositories;

import com.homeproject.blog.backend.persistence.entity.TagEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends CrudRepository<TagEntity, Long> {
    Page<TagEntity> findAllByIdAndName(PageRequest pageRequest, Long id, String name);
}
