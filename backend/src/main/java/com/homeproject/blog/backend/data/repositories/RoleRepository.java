package com.homeproject.blog.backend.data.repositories;

import com.homeproject.blog.backend.data.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role getRoleByName(Role.RoleName name);
}
