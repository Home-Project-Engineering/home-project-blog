package com.homeproject.blog.backend.data.repository;

import com.homeproject.blog.backend.data.entity.PostEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface PostRepository extends CrudRepository<PostEntity, Long> {

//    @Query(value = "SELECT * FROM posts_tags p WHERE ")
//    Collection<PostEntity> findAllPosts(@Param("id") String id,
//                                        @Param("tag_id") String tag_id,
//                                        @Param("tag_name") String tag_name,
//                                        @Param("author_name") String author_name,
//                                        @Param("sort") String sort,
//                                        @Param("page_num") String page_num,
//                                        @Param("page_size") String page_size
//                                        );
}
