package com.example.blog.backend.repository.repos;


import com.example.blog.backend.repository.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface PostRepo extends JpaRepository<PostEntity, Long>, JpaSpecificationExecutor<PostEntity> {
}
