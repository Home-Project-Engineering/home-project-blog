package com.softserveinc.ita.homeprojectblog.repository;

import com.softserveinc.ita.homeprojectblog.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByName(RoleEntity.NameEnum name);

}
