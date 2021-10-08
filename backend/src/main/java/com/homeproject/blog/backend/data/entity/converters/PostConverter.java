package com.homeproject.blog.backend.data.entity.converters;

import com.homeproject.blog.backend.data.entity.PostEntity;
import com.homeproject.blog.backend.dtos.Post;

import java.util.Collection;

public interface PostConverter {

    Post entityToPost(PostEntity entity);

    PostEntity postToEntity(Post post);

    Collection<Post> entitiesToPosts(Collection<PostEntity> entities);
}
