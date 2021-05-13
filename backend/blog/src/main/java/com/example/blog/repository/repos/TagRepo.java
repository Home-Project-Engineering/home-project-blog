package com.example.blog.repository.repos;

import com.example.blog.repository.entities.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepo extends JpaRepository<TagEntity, Long>, JpaSpecificationExecutor<TagEntity> {
    Optional<TagEntity> findByName (String name);
}
