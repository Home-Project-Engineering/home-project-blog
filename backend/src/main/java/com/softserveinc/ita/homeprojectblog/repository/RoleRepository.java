package com.softserveinc.ita.homeprojectblog.repository;

import com.softserveinc.ita.homeprojectblog.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
}
