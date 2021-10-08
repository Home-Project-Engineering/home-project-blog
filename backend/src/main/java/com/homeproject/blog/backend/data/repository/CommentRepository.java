package com.homeproject.blog.backend.data.repository;

import com.homeproject.blog.backend.data.entity.CommentEntity;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<CommentEntity, Long> {
}
