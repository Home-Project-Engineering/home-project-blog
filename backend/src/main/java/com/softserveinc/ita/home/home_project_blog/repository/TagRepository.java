package com.softserveinc.ita.home.home_project_blog.repository;

import com.softserveinc.ita.home.home_project_blog.repository.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository extends PagingAndSortingRepository<Tag, Long>, JpaSpecificationExecutor<Tag> {
    Page<Tag> findByNameAndId(String name, Long id, Pageable paging);

    Optional<Tag> findByName(String name);

    Page<Tag> findByName(String name, Pageable paging);

    Page<Tag> findById(Long Id, Pageable paging);
}
