package com.itacademy.blog.data.repository;

import com.itacademy.blog.data.Entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TagRepo extends JpaRepository<Tag, Long>, JpaSpecificationExecutor<Tag> {
}
