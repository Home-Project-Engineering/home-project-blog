package com.homeproject.blog.backend.repositories;

import com.homeproject.blog.backend.classes.Tag;
import org.springframework.data.repository.CrudRepository;

public interface TagRepository extends CrudRepository<Tag, Long> {
}
