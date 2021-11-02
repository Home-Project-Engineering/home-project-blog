package com.homeproject.blog.backend.data.repositories;

import com.homeproject.blog.backend.data.entities.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "select comment from Comment comment where comment.post.id = :post_id and (:id is null or comment.id = :id) and (:authorName is null or comment.author.name = :authorName) ")
    Page<Comment> findAllByParameters(Pageable pageRequest, Long post_id, Long id, String authorName);

    @Query(value = "select comment from Comment comment where comment.author.id = :userId and (:id is null or comment.id = :id)")
    Page<Comment> findByUserId(Pageable pageRequest,Long id, Long userId);

    @Query(value = "select comment from Comment comment where comment.author.id = :userId and comment.id = :id")
    Optional<Comment> findByAuthorAndId(Long id, Long userId);
}
