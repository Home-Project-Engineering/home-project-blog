package com.homeproject.blog.backend.database.repositories;

import com.homeproject.blog.backend.persistence.entity.PostEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends CrudRepository<PostEntity, Long> {
}
