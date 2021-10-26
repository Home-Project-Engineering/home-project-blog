package com.homeproject.blog.backend.data.repositories;

import com.homeproject.blog.backend.data.entities.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    @Query(value = "SELECT tag FROM Tag tag WHERE (:id is null or tag.id = :id) and (:name is null or tag.name = :name)")
    Page<Tag> findAllByIdAndName(Pageable pageRequest, Long id, String name);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "delete from post_tags where tag_id = :id", nativeQuery = true)
    void deleteRelation(Long id);

    Optional<Tag> findByName(String name);
}