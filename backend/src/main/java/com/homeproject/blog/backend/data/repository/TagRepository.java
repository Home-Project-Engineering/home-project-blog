package com.homeproject.blog.backend.data.repository;

import com.homeproject.blog.backend.data.entity.TagEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<TagEntity, Long> {
    @Query(value = "SELECT tag FROM TagEntity tag WHERE (:id is null or tag.id = :id) and (:name is null or tag.name = :name)")
    Page<TagEntity> findAllByIdAndName(Pageable pageRequest, Long id, String name);
}
