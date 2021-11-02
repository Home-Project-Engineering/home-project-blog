package com.homeproject.blog.backend.data.repository;

import com.homeproject.blog.backend.data.entity.RoleTypeEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RoleTypeRepository extends CrudRepository<RoleTypeEntity, Long> {

    @Query(value = "SELECT role FROM RoleTypeEntity role WHERE role.name = :name")
    RoleTypeEntity findByName(String name);
}
