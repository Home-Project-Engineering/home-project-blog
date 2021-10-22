package com.homeproject.blog.backend.database.repositories;

import com.homeproject.blog.backend.persistence.entity.CommentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends CrudRepository<CommentEntity, Long> {

}
