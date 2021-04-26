package com.itacademy.blog.data.repository;

import com.itacademy.blog.data.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Long>, JpaSpecificationExecutor<Comment> {

    Optional<Comment> findOneByPostIdAndId(Long post_Id, Long id);
    Optional<Comment> findOneByUserIdAndId(Long user_id, Long id);
}
