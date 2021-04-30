package com.softserveinc.ita.home.home_project_blog.repository;

import com.softserveinc.ita.home.home_project_blog.repository.entity.Post;
import com.softserveinc.ita.home.home_project_blog.repository.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends PagingAndSortingRepository<Post, Long>, JpaSpecificationExecutor<Post> {
    Page<Post> findById(Long Id, Pageable paging);
}
