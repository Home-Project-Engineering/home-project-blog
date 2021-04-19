package com.itacademy.blog.data.repository;

import com.itacademy.blog.data.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Long>, JpaSpecificationExecutor<Comment> {

}
