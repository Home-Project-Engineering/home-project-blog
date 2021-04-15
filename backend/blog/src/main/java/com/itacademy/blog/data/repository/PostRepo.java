package com.itacademy.blog.data.repository;

import com.itacademy.blog.data.Entity.Post;
import com.itacademy.blog.data.Entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PostRepo extends JpaRepository<Post, Long>, JpaSpecificationExecutor<Post> {
}
