package com.homeproject.blog.backend.data.repositories;

import com.homeproject.blog.backend.data.entities.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query(value = "SELECT Post FROM Post post INNER JOIN Tag tag WHERE (:id is null or post.id = :id) and (:tagId is null or tag.id = :tagId) and (:tagName is null or tag.name = :tagName) and (:authorName is null or post.author.name = :authorName)")
    Page<Post> findAllBy (PageRequest pageRequest, Long id, Long tagId, String tagName, String authorName);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE from post_tags WHERE post_id = :id", nativeQuery = true)
    void deleteRelation(Long id);
}
