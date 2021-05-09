package com.softserveinc.ita.homeprojectblog.repository;

import com.softserveinc.ita.homeprojectblog.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface PostRepository
        extends JpaRepository<PostEntity, BigDecimal>, JpaSpecificationExecutor<PostEntity> {
    Optional<PostEntity> findByUserIdAndId(BigDecimal id, BigDecimal id1);
}
