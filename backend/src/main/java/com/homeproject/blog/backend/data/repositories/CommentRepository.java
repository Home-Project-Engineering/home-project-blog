package com.homeproject.blog.backend.data.repositories;

import com.homeproject.blog.backend.data.entities.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "select comment from Comment comment where comment.post.id = :post_id and (:id is null or comment.id = :id) and (:authorName is null or comment.author.name = :authorName) ")
    Page<Comment> findAllByParameters(Pageable pageRequest, Long post_id, Long id, String authorName);
}
