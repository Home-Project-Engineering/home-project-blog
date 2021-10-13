package com.homeproject.blog.backend.businesslayer.services;

import com.homeproject.blog.backend.businesslayer.dto.PostDTO;
import com.homeproject.blog.backend.businesslayer.dto.TagDTO;
import com.homeproject.blog.backend.data.entities.Post;
import com.homeproject.blog.backend.data.entities.Tag;
import com.homeproject.blog.backend.data.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    ConversionService conversionService;

    private static String currentDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    @Override
    public PostDTO createPost(PostDTO postDTO) {
        Post newPost = conversionService.convert(postDTO, Post.class);
        newPost.setCreatedOn(currentDate());
        newPost.setUpdatedOn(currentDate());
        postRepository.save(newPost);
        return conversionService.convert(newPost, PostDTO.class);
    }

    @Override
    public PostDTO updatePost(Long id, PostDTO postDTO) {
        Post newPost = postRepository.getById(id);
        newPost.setTags(postDTO.getTags().stream().map(tag -> conversionService.convert(tag, Tag.class)).collect(Collectors.toList()));
        newPost.setText(postDTO.getText());
        newPost.setTitle(postDTO.getTitle());
        newPost.setPreviewAttachment(postDTO.getPreviewAttachment());
        newPost.setUpdatedOn(currentDate());
        postRepository.save(newPost);
        return conversionService.convert(newPost, PostDTO.class);
    }

    @Override
    public PostDTO readPost(Long id) {
        return conversionService.convert(postRepository.getById(id),PostDTO.class) ;
    }

    @Override
    public Page<PostDTO> getPosts(Long id, Long tagId, String tagName, String authorName, PageRequest pageRequest) {
        Page<Post> allByIdAndName = postRepository.findAllBy(pageRequest, id, tagId, tagName, authorName);
        return new PageImpl<>(allByIdAndName.stream().map(post -> conversionService.convert(post, PostDTO.class)).collect(Collectors.toList()), pageRequest, allByIdAndName.getTotalElements());
    }

    @Override
    public void deletePost(Long id) {
        postRepository.deleteRelation(id);
        postRepository.deleteById(id);
    }
}
