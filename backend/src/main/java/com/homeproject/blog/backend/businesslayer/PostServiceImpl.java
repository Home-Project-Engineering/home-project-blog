package com.homeproject.blog.backend.businesslayer;

import com.homeproject.blog.backend.businesslayer.converters.UserConverter;
import com.homeproject.blog.backend.data.entity.PostEntity;
import com.homeproject.blog.backend.data.entity.TagEntity;
import com.homeproject.blog.backend.businesslayer.converters.PostConverter;
import com.homeproject.blog.backend.data.repository.PostRepository;
import com.homeproject.blog.backend.dtos.Post;
import com.homeproject.blog.backend.exceptions.PostNotFoundException;
import com.homeproject.blog.backend.security.SecurityService;
import com.homeproject.blog.backend.supportclasses.CurrentDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private TagService tagService;
    @Autowired
    private PostConverter postConverter;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private UserConverter userConverter;


    @Override
    public Post createPost(Post post) {
        String date = CurrentDate.getDate();
        List<TagEntity> tagEntities = tagService.identifyTags(post.getTags());
        PostEntity newPost = new PostEntity();
        newPost.setTags(tagEntities);
        newPost.setTitle(post.getTitle());
        newPost.setText(post.getText());
        newPost.setAuthor(userConverter.userToEntity(securityService.findLoggedInUser()));
        newPost.setPreviewAttachment(post.getPreviewAttachment());
        newPost.setUpdatedOn(date);
        newPost.setCreatedOn(date);
        PostEntity savedPost = postRepository.save(newPost);
        return postConverter.entityToPost(savedPost);
    }

    @Override
    public Post updatePost(Long id, Post changes) {
        List<TagEntity> tagEntities = tagService.identifyTags(changes.getTags());
        PostEntity entity = verifyPostExisting(id);
        entity.setText(changes.getText());
        entity.setTags(tagEntities);
        entity.setTitle(changes.getTitle());
        entity.setPreviewAttachment(changes.getPreviewAttachment());
        entity.setUpdatedOn(CurrentDate.getDate());
        PostEntity updatedEntity = postRepository.save(entity);
        return postConverter.entityToPost(updatedEntity);
    }

    private PostEntity verifyPostExisting(Long id) {
        Optional<PostEntity> result = postRepository.findById(id);
        if (result.isEmpty()) {
            throw new PostNotFoundException();
        }
        return result.get();
    }

    @Override
    public Post readPost(Long id) {
        PostEntity entity = verifyPostExisting(id);
        return postConverter.entityToPost(entity);
    }

    @Override
    public PostEntity findPostEntity(Long postId) {
        return verifyPostExisting(postId);
    }

    @Override
    public Page<Post> getPosts(Long id, Long tagId, String tagName, String authorName, String sort, Integer pageNum, Integer pageSize) {
        Sort sorting;
        if (sort != null) {
            if (sort.charAt(0) == '-') {
                sorting = Sort.by(sort.substring(1)).descending();
            } else {
                sorting = Sort.by(sort);
            }
        } else {
            sorting = Sort.by("id").descending();
        }
        pageNum = pageNum == null ? pageNum = 0 : pageNum;
        pageSize = pageSize == null ? pageSize = 10 : pageSize;
        PageRequest request = PageRequest.of(pageNum, pageSize, sorting);
        Page<PostEntity> entities = postRepository.findPostsByParameters(request, id, tagId, tagName, authorName);
        return new PageImpl<>(entities.stream().map(postConverter::entityToPost).collect(Collectors.toList()), request, entities.getTotalElements());
    }

    @Override
    public void deletePost(Long id) {
        verifyPostExisting(id);
        postRepository.deleteById(id);
    }
}
