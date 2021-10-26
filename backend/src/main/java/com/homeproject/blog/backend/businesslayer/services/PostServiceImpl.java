package com.homeproject.blog.backend.businesslayer.services;

import com.homeproject.blog.backend.businesslayer.dto.PostDTO;
import com.homeproject.blog.backend.data.entities.Post;
import com.homeproject.blog.backend.data.entities.Tag;
import com.homeproject.blog.backend.data.repositories.PostRepository;
import com.homeproject.blog.backend.exceptions.PostNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    ConversionService conversionService;

    @Override
    public PostDTO createPost(PostDTO postDTO) {
        Post newPost = conversionService.convert(postDTO, Post.class);
        newPost.setCreatedOn(OffsetDateTime.now());
        newPost.setUpdatedOn(OffsetDateTime.now());
        postRepository.save(newPost);
        return conversionService.convert(newPost, PostDTO.class);
    }

    @Override
    public PostDTO updatePost(Long id, PostDTO postDTO) {
        if (postRepository.findById(id).isEmpty()) {
            throw new PostNotFoundException();
        }
        Post post = postRepository.getById(id);
        post.setTags(postDTO.getTags().stream().map(tag -> conversionService.convert(tag, Tag.class)).collect(Collectors.toList()));
        post.setText(postDTO.getText());
        post.setTitle(postDTO.getTitle());
        post.setPreviewAttachment(postDTO.getPreviewAttachment());
        post.setUpdatedOn(OffsetDateTime.now());
        postRepository.save(post);
        return conversionService.convert(post, PostDTO.class);

    }

    @Override
    public PostDTO readPost(Long id) {
        Optional<Post> post = postRepository.findById(id);
        if(post.isEmpty()){
            throw new PostNotFoundException();
        }
        return conversionService.convert(post.get(),PostDTO.class) ;
    }

    @Override
    public Page<PostDTO> getPosts(Long id, Long tagId, String tagName, String authorName, Pageable pageRequest) {
        Page<Post> allByIdAndName = postRepository.findAllByIdAndAuthorAndTags(pageRequest, id, tagId, tagName, authorName);
        return new PageImpl<>(allByIdAndName.stream().map(post -> conversionService.convert(post, PostDTO.class)).collect(Collectors.toList()), pageRequest, allByIdAndName.getTotalElements());
    }

    @Override
    public void deletePost(Long id) {
        if(postRepository.findById(id).isEmpty()){
            throw new PostNotFoundException();
        }
        postRepository.deleteRelation(id);
        postRepository.deleteById(id);
    }
}
