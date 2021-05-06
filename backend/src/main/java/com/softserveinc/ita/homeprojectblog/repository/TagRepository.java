package com.softserveinc.ita.homeprojectblog.repository;

import com.softserveinc.ita.homeprojectblog.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<TagEntity, BigDecimal> {

    Optional<TagEntity> findByName(String name);
}
