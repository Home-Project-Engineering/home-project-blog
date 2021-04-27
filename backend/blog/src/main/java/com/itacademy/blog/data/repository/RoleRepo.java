package com.itacademy.blog.data.repository;

import com.itacademy.blog.data.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role, Long> {
    Optional<Role> findOneByRole(String role);
}
