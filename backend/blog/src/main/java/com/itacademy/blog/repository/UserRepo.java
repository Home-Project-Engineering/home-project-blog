package com.itacademy.blog.repository;

import com.itacademy.blog.repository.Entity.UserEntity;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepo extends JpaRepository<UserEntity, Integer> {
    UserEntity findById(Long id);
    List<UserEntity> findAllById(Long id, Pageable pageable);
    List<UserEntity> findAllByName(String name, Pageable pageable);
    List<UserEntity> findAllByNameAndId(String name, Long id, Pageable pageable);

}
