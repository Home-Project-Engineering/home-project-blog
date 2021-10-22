package com.homeproject.blog.backend.business.services.impl;

import com.homeproject.blog.backend.business.convertersBetweenServiceAndController.PostConverter;
import com.homeproject.blog.backend.business.convertersBetweenServiceAndController.TagConverter;
import com.homeproject.blog.backend.business.models.additional.Date;
import com.homeproject.blog.backend.database.repositories.PostRepository;
import com.homeproject.blog.backend.business.services.PostService;
import com.homeproject.blog.backend.business.models.DTO.Post;
import com.homeproject.blog.backend.business.services.TagService;
import com.homeproject.blog.backend.persistence.entity.PostEntity;
import com.homeproject.blog.backend.persistence.entity.TagEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class

PostServiceImpl implements PostService {
    @Autowired
    private PostConverter postConverter;
    @Autowired
    private TagConverter tagConverter;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private TagService tagService;


    @Override
    public Post createPost(Post post) {
        PostEntity newPost = new PostEntity();
        List<TagEntity> tags = tagService.identifyTags(tagConverter.tagsToEntities(post.getTags()));
        newPost.setText(post.getText());
        newPost.setAuthor(post.getAuthor());
        newPost.setTags(tags);
        newPost.setTitle(post.getTitle());
        newPost.setPreviewAttachment(post.getPreviewAttachment());
        newPost.setUpdatedOn(Date.getCurrentDate());
        newPost.setCreatedOn(Date.getCurrentDate());
        PostEntity savedPost = postRepository.save(newPost);
        return postConverter.entityToPost(savedPost);
    }

    @Override
    public Post updatePost(Long id, Post postUpdate) {
        List<TagEntity> tags = tagService.identifyTags(tagConverter.tagsToEntities(postUpdate.getTags()));
        PostEntity entity = isPostExisting(id);
        entity.setText(postUpdate.getText());
        entity.setTags(tags);
        entity.setTitle(postUpdate.getTitle());
        entity.setPreviewAttachment(postUpdate.getPreviewAttachment());
        entity.setUpdatedOn(Date.getCurrentDate());
        PostEntity updatedEntity = postRepository.save(entity);
        return postConverter.entityToPost(updatedEntity);
    }

    @Override
    public Post readPost(Long id) {
        PostEntity post = isPostExisting(id);
        return postConverter.entityToPost(post);
    }

    @Override
    public Collection<Post> getPosts() {
        return null;
    }

    @Override
    public void deletePost(Long id) {
        isPostExisting(id);
        postRepository.deleteById(id);
    }


    private PostEntity isPostExisting(Long id) {
        Optional<PostEntity> result = postRepository.findById(id);
        return result.get();
    }
}
