package com.itacademy.blog.data.repository;

import com.itacademy.blog.data.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CommentRepo extends JpaRepository<Comment, Long>, JpaSpecificationExecutor<Comment> {
}
