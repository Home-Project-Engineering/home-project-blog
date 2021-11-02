package com.homeproject.blog.backend.business.services.impl;

import com.homeproject.blog.backend.presentation.converters.PostConverter;
import com.homeproject.blog.backend.presentation.converters.TagConverter;
import com.homeproject.blog.backend.business.models.additional.Date;
import com.homeproject.blog.backend.database.repositories.PostRepository;
import com.homeproject.blog.backend.business.services.PostService;
import com.homeproject.blog.backend.business.models.DTO.PostDTO;
import com.homeproject.blog.backend.business.services.TagService;
import com.homeproject.blog.backend.persistence.entity.PostEntity;
import com.homeproject.blog.backend.persistence.entity.TagEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostConverter postConverter;
    @Autowired
    private TagConverter tagConverter;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private TagService tagService;


    @Override
    public PostDTO createPost(PostDTO postDTO) {
        PostEntity newPost = new PostEntity();
        List<TagEntity> tags = tagService.identifyTags(tagConverter.tagsToEntities(postDTO.getTags()));
        newPost.setText(postDTO.getText());
        newPost.setAuthorDTO(postDTO.getAuthorDTO());
        newPost.setTags(tags);
        newPost.setTitle(postDTO.getTitle());
        newPost.setPreviewAttachment(postDTO.getPreviewAttachment());
        newPost.setUpdatedOn(Date.getCurrentDate());
        newPost.setCreatedOn(Date.getCurrentDate());
        PostEntity savedPost = postRepository.save(newPost);
        return postConverter.entityToPost(savedPost);
    }

    @Override
    public PostDTO updatePost(Long id, PostDTO postDTOUpdate) {
        List<TagEntity> tags = tagService.identifyTags(tagConverter.tagsToEntities(postDTOUpdate.getTags()));
        PostEntity entity = isPostExisting(id);
        entity.setText(postDTOUpdate.getText());
        entity.setTags(tags);
        entity.setTitle(postDTOUpdate.getTitle());
        entity.setPreviewAttachment(postDTOUpdate.getPreviewAttachment());
        entity.setUpdatedOn(Date.getCurrentDate());
        PostEntity updatedEntity = postRepository.save(entity);
        return postConverter.entityToPost(updatedEntity);
    }

    @Override
    public PostDTO readPost(Long id) {
        PostEntity post = isPostExisting(id);
        return postConverter.entityToPost(post);
    }

    @Override
    public Collection<PostDTO> getPosts(Map<String, String> parameters) {
        Iterable<PostEntity> entities = postRepository.findAll();
        ArrayList<PostEntity> list = new ArrayList<>();
        entities.forEach(list::add);
        return postConverter.entitiesToPosts(list);
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
