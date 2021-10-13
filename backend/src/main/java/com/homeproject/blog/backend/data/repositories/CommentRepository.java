package com.homeproject.blog.backend.data.repositories;

import com.homeproject.blog.backend.data.entities.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
}
