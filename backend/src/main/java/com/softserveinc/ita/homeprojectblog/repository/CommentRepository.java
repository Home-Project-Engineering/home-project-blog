package com.softserveinc.ita.homeprojectblog.repository;

import com.softserveinc.ita.homeprojectblog.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface CommentRepository
        extends JpaRepository<CommentEntity, BigDecimal>, JpaSpecificationExecutor<CommentEntity> {

    Optional<CommentEntity> findByPostIdAndId(BigDecimal postId, BigDecimal id);

    Optional<CommentEntity> findByUserIdAndId(BigDecimal id, BigDecimal id1);

}
