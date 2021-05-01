package com.softserveinc.ita.homeprojectblog.repository;

import com.softserveinc.ita.homeprojectblog.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, BigDecimal> {

    Page<UserEntity> findByName(String name, Pageable pageable);

    Optional<UserEntity> findByName(String name);

}
