package com.homeproject.blog.backend.businesslayer;

import com.homeproject.blog.backend.data.entity.AuthorEntity;
import com.homeproject.blog.backend.data.entity.PostEntity;
import com.homeproject.blog.backend.data.entity.TagEntity;
import com.homeproject.blog.backend.data.entity.converters.PostConverter;
import com.homeproject.blog.backend.data.repository.PostRepository;
import com.homeproject.blog.backend.dtos.Author;
import com.homeproject.blog.backend.dtos.Post;
import com.homeproject.blog.backend.exceptions.PostNotFoundException;
import com.homeproject.blog.backend.supportclasses.CurrentDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private TagService tagService;
    @Autowired
    private PostConverter postConverter;

    private static Author defaultAuthor = new Author("Paul", "Pavlo", "Ponomarenko");

    @Override
    public Post createPost(Post post) {
        String date = CurrentDate.getDate();
        List<TagEntity> tagEntities = tagService.identifyTags(post.getTags());
        PostEntity newPost = new PostEntity();
        newPost.setTags(tagEntities);
        newPost.setTitle(post.getTitle());
        newPost.setText(post.getText());
        newPost.setAuthor(new AuthorEntity(defaultAuthor));
        newPost.setPreviewAttachment(post.getPreviewAttachment());
        newPost.setUpdatedOn(date);
        newPost.setCreatedOn(date);
        PostEntity savedPost = postRepository.save(newPost);
        return postConverter.entityToPost(savedPost);
    }

    @Override
    public Post updatePost(Long id, Post changes) throws PostNotFoundException {
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

    private PostEntity verifyPostExisting(Long id) throws PostNotFoundException {
        Optional<PostEntity> result = postRepository.findById(id);
        if (result.isEmpty()) {
            throw new PostNotFoundException();
        }
        return result.get();
    }

    @Override
    public Post readPost(Long id) throws PostNotFoundException {
        PostEntity entity = verifyPostExisting(id);
        return postConverter.entityToPost(entity);
    }

    @Override
    public Collection<Post> getPosts(Map<String, String> parameters) {
        Iterable<PostEntity> entities = postRepository.findAll();
        ArrayList<PostEntity> list = new ArrayList<>();
        entities.forEach(list::add);
        return postConverter.entitiesToPosts(list);
    }

    @Override
    public Collection<Post> sortPosts(Collection<Post> posts, Map<String, String> parameters) {
        return posts;
    }

    @Override
    public void deletePost(Long id) throws PostNotFoundException {
        verifyPostExisting(id);
        postRepository.deleteById(id);
    }
}
