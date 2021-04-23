package com.itacademy.blog.services.service.impl;

import com.itacademy.blog.data.entity.Post;
import com.itacademy.blog.data.repository.PostRepo;
import com.itacademy.blog.data.repository.TagRepo;
import com.itacademy.blog.services.DTO.PostDTO;
import com.itacademy.blog.services.mapper.PostMapper;
import com.itacademy.blog.services.service.PostService;
import com.itacademy.blog.services.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepo postRepo;
    private final TagRepo tagRepo;
    private final UserService userService;


    @Override
    public PostDTO createPost(PostDTO createPostDto) {
        Post entityToCreate = PostMapper.INSTANCE.convert(createPostDto);
        entityToCreate.getTags().removeIf(tag -> tagRepo.findByName(tag.getName()).isPresent());

        for(int i = 0; i < createPostDto.getTags().size();i++){
            if(tagRepo.findByName(createPostDto.getTags().get(i).getName()).isPresent()){
                entityToCreate.getTags().add(tagRepo.findByName(createPostDto.getTags().get(i).getName()).get());
            }
        }
        entityToCreate.setCreatedOn(OffsetDateTime.now());
        entityToCreate.setUser(userService.getCurrentUserEntity());
        postRepo.save(entityToCreate);
        return PostMapper.INSTANCE.convert(entityToCreate);
    }

    @Override
    public PostDTO updatePost(Long id, PostDTO updatePostDto) {
        Optional<Post> optionalPost = postRepo.findById(id);
        if (optionalPost.isPresent()) {
            Post fromDB = optionalPost.get();
            fromDB.setUpdatedOn(OffsetDateTime.now());
            if (updatePostDto.getPreviewAttachment() != null) {
                fromDB.setPreviewAttachment(updatePostDto.getPreviewAttachment());
            }
            if (updatePostDto.getText() != null) {
                fromDB.setText(updatePostDto.getText());
            }

            if (updatePostDto.getTitle() != null) {
                fromDB.setTitle(updatePostDto.getTitle());
            }

            //to do may be add new method to update and delete tags in post
            if (updatePostDto.getTags() != null) {
                fromDB.getTags().clear();
                fromDB.getTags().addAll(updatePostDto.getTags());
                fromDB.getTags().removeIf(tag -> tagRepo.findByName(tag.getName()).isPresent());

                for(int i = 0; i < updatePostDto.getTags().size();i++) {
                    if (tagRepo.findByName(updatePostDto.getTags().get(i).getName()).isPresent()) {
                        fromDB.getTags().add(tagRepo.findByName(updatePostDto.getTags().get(i).getName()).get());
                    }
                }
            }

            postRepo.save(fromDB);
            return PostMapper.INSTANCE.convert(fromDB);

        } else {
            throw new EntityNotFoundException("Post with id:" + id + " is not found");
        }
    }

    @Override
    public List<PostDTO> findPosts(Integer pageNumber, Integer pageSize, String sort, Specification<Post> specification) {
        List<Post> posts = postRepo.findAll(specification, PageRequest
                .of(pageNumber - 1, pageSize, getSort(sort))).toList();
/*
        posts.forEach(post -> post.getUser().setPassword("********"));
*/
        return PostMapper.INSTANCE.convert(posts);
    }
    private Sort getSort(String sort) {
        StringBuilder str = new StringBuilder(sort);

        if(str.charAt(0) == '-'){
            str.deleteCharAt(0);
            return Sort.by(Sort.Direction.DESC, str.toString());}

        return Sort.by(Sort.Direction.ASC, str.toString());
    }

    @Override
    public PostDTO getPostById(Long id) {
        Post toGet = postRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Post with id:" + id + " is not found"));
        return PostMapper.INSTANCE.convert(toGet);
    }

    @Override
    public PostDTO deletePost(Long id) {
        Post toDelete = postRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("User with id:" + id + " is not found"));
        postRepo.deleteById(id);

        return PostMapper.INSTANCE.convert(toDelete);    }
}
