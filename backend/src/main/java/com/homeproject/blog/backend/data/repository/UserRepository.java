package com.homeproject.blog.backend.data.repository;

import com.homeproject.blog.backend.data.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
}
