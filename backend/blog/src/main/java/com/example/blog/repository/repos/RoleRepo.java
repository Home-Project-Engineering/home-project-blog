package com.example.blog.repository.repos;

import com.example.blog.repository.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<RoleEntity, Long> {

    RoleEntity findOneByName(RoleEntity.RoleEnum role);

}
