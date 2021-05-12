package com.softserveinc.ita.home.home_project_blog.service;

import com.softserveinc.ita.home.home_project_blog.repository.PostRepository;
import com.softserveinc.ita.home.home_project_blog.repository.TagRepository;
import com.softserveinc.ita.home.home_project_blog.repository.entity.Post;
import com.softserveinc.ita.home.home_project_blog.service.dto.PostDto;
import com.softserveinc.ita.home.home_project_blog.service.dto.TagDto;
import com.softserveinc.ita.home.home_project_blog.service.mapper.PostMapperService;
import com.softserveinc.ita.home.home_project_blog.service.mapper.TagMapperService;
import com.softserveinc.ita.home.home_project_blog.specification.SpecificationService;
import com.softserveinc.ita.home.home_project_blog.validation.Const;
import com.softserveinc.ita.home.home_project_blog.validation.MismatchException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.*;

@Validated
@RequiredArgsConstructor
@Service
public class PostService implements IPostService {

    private final PostRepository postRepository;
    private final TagRepository tagRepository;
    private final PostMapperService postMapper;
    private final TagMapperService tagMapper;
    private final IUserService userService;
    private final SpecificationService<Post> postSpecificationService;


    @Override
    public Page<PostDto> findAll(Long id, Long tag_id, String tag_name, Long user_id, Integer pageNum, Integer pageSize, String sort){
        Pageable paging = GeneralService.pagination(pageNum, pageSize, sort);
        Map<String, String> filter = new HashMap<>();
        filter.put("id", id!=null?id.toString():null);
        filter.put("tags.id", tag_id!=null?tag_id.toString():null);
        filter.put("tags.name", tag_name);
        filter.put("user.id", user_id!=null?user_id.toString():null);

        Specification<Post> specification = postSpecificationService.getSpecification(filter);
        Page<Post> pagePost = postRepository.findAll(specification, paging);
        return postMapper.toPagePostDto(pagePost);
    }

    @Override
    public PostDto getById(Long id) {
        return postMapper.toPostDto(postRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(Const.POST_DOESNT_EXIST)));
    }

    @Override
    public PostDto save(@Valid PostDto post) {
        post.setUser(userService.getCurrentUser());
        post.setTags(updateTags(post.getTags()));
        return postMapper.toPostDto(postRepository.save(postMapper.toPost(post)));
    }

    private Set<TagDto> updateTags(Set<TagDto> tags) {
        Set<TagDto> tags2 = new HashSet<>();
        for (TagDto tag : tags) {
            tags2.add(tagMapper.toTagDto(
                    tagRepository.findByName(tag.getName())
                            .orElseGet(() -> tagRepository.save(tagMapper.toTag(tag)))
            ));
        }
        return tags2;
    }

    @Override
    public PostDto update(PostDto oldPost, PostDto postUpdates) {
        oldPost.setTags(updateTags(postUpdates.getTags()));
        oldPost.setText(postUpdates.getText());
        oldPost.setTitle(postUpdates.getTitle());
        oldPost.setPreviewAttachment(postUpdates.getPreviewAttachment());
        return postMapper.toPostDto(postRepository.save(postMapper.toPost(oldPost)));
    }

    @Override
    public PostDto update(Long id, @Valid PostDto post) {
        return update(getById(id), post);
    }


    @Override
    public void delete(Long id) {
        if (!postRepository.existsById(id)) {
            throw new EntityNotFoundException(Const.POST_DOESNT_EXIST);
        }
        postRepository.deleteById(id);
    }

    //***************CURRENT USER*************************

    @Override
    public Page<PostDto> getPostsByCurrentUser(Long post_id, Long tag_id, String tag_name, Integer pageNum, Integer pageSize, String sort) {
        return findAll(post_id, tag_id, tag_name, userService.getCurrentUser().getId(), pageNum, pageSize, sort);
    }

    public PostDto getPostByIdByCurrentUser(Long post_id) {
        PostDto post = getById(post_id);
        if (!post.getUser().equals(userService.getCurrentUser())) {
            throw new MismatchException(Const.POST_DOESNT_ADHERE_TO_THE_USER);
        }
        return post;
    }

    @Override
    public PostDto updatePostByCurrentUser(Long post_id, @Valid PostDto post) {
        return update(getPostByIdByCurrentUser(post_id), post);
    }

    @Override
    public void deletePostByCurrentUser(Long post_id) {
        getPostByIdByCurrentUser(post_id);
        postRepository.deleteById(post_id);
    }
}
