package com.homeproject.blog.backend.repositories;


import com.homeproject.blog.backend.classes.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
    Comment findById(Integer id);
}
