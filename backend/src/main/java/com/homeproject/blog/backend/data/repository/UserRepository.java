package com.homeproject.blog.backend.data.repository;

import com.homeproject.blog.backend.data.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    @Query(value = "SELECT user FROM UserEntity user WHERE user.name = :name")
    UserEntity findUserByName(String name);

    @Query(value = "SELECT user FROM UserEntity user WHERE (:id is null or user.id = :id) and (:name is null or user.name = :name)")
    Page<UserEntity> findAllByIdAndName(Pageable pageRequest, Long id, String name);
}
