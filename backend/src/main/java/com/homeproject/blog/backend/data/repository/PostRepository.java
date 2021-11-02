package com.homeproject.blog.backend.data.repository;

import com.homeproject.blog.backend.data.entity.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface PostRepository extends JpaRepository<PostEntity, Long> {

    @Query(value = "SELECT DISTINCT post FROM PostEntity post LEFT JOIN post.tags tags WHERE (:id is null or post.id = :id) and (:tag_id is null or tags.id = :tag_id) and (:tag_name is null or tags.name = :tag_name) and (:author_name is null or post.author.name = :author_name)")
    Page<PostEntity> findPostsByParameters(Pageable page,
                                           @Param("id") Long id,
                                           @Param("tag_id") Long tag_id,
                                           @Param("tag_name") String tag_name,
                                           @Param("author_name") String author_name
                                           );
}
