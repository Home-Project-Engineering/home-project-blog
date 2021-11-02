package com.homeproject.blog.backend.data.repositories;

import com.homeproject.blog.backend.data.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByName(String name);

    @Query(value = "SELECT user FROM User user where (:id is null or user.id = :id) and (:name is null or user.name = :name)")
    Page<User> findAllByIdAndAndName(Pageable pageRequest, Long id, String name);
}
