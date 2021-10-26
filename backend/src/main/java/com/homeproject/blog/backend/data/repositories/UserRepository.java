package com.homeproject.blog.backend.data.repositories;

import com.homeproject.blog.backend.data.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
