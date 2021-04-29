package com.softserveinc.ita.home.home_project_blog.repository;

import com.softserveinc.ita.home.home_project_blog.repository.entity.Tag;
import com.softserveinc.ita.home.home_project_blog.repository.entity.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends PagingAndSortingRepository<Tag, Long>, JpaSpecificationExecutor<Tag> {
}
