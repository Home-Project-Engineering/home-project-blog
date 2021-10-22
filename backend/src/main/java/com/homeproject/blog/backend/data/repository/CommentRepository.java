package com.homeproject.blog.backend.data.repository;

import com.homeproject.blog.backend.data.entity.CommentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends CrudRepository<CommentEntity, Long> {

    @Query(value="SELECT comment FROM CommentEntity comment WHERE (:id is null or comment.id = :id) and (:name is null or comment.author = :authorName)")
    Page<CommentEntity> findAllByIdAndName(Pageable pageRequest, Long id, String authorName);
}
