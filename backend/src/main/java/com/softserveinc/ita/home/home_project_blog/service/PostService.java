package com.softserveinc.ita.home.home_project_blog.service;

import com.softserveinc.ita.home.home_project_blog.repository.PostRepository;
import com.softserveinc.ita.home.home_project_blog.repository.TagRepository;
import com.softserveinc.ita.home.home_project_blog.repository.entity.Post;
import com.softserveinc.ita.home.home_project_blog.repository.entity.Tag;
import com.softserveinc.ita.home.home_project_blog.service.dto.PostDto;
import com.softserveinc.ita.home.home_project_blog.service.mapper.PostMapperService;
import com.softserveinc.ita.home.home_project_blog.validation.Const;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Validated
@RequiredArgsConstructor
@Service
public class PostService implements IPostService {

    private final PostRepository postRepository;
    private final TagRepository tagRepository;
    private final PostMapperService mapper;
    private final PasswordEncoder passwordEncoder;
    private final IUserService userService;

    @Override
    public Page<PostDto> findAll(Long id, String title, Integer pageNum, Integer pageSize, String sort) {
        Pageable paging = GeneralService.pagination(pageNum, pageSize, sort);
        Page<Post> postsPage;
//        if ((title != null) && (id != null)) {
//            postsPage = repository.findByTitleAndId(title, id, paging);
//        } else if (title != null) {
//            postsPage = repository.findByTitle(title, paging);
//        } else
        if (id != null) {
            postsPage = postRepository.findById(id, paging);
        } else {
            postsPage = postRepository.findAll(paging);
        }
        return mapper.toPagePostDto(postsPage);
    }

    @Override
    public PostDto getById(Long id) {
        return mapper.toPostDto(postRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(Const.POST_DOESNT_EXIST)));
    }


//    private void throwIfEmailIsNotUnique(String email) {
//        if (repository.existsByEmail(email)) {
//            throw new NotUniqueException(Const.EMAIL_IS_NOT_UNIQUE);
//        }
//    }
//
//    private void throwIfNameIsNotUnique(String name) {
//        if (repository.existsByName(name)) {
//            throw new NotUniqueException(Const.NAME_IS_NOT_UNIQUE);
//        }
//    }

    @Override
    public PostDto save(@Valid PostDto post) {
        Date now = new Date(System.currentTimeMillis());
//        post.setCreatedOn(now);
//        post.setUpdatedOn(now);
        post.setUser(userService.getCurrentUser());
        Post post1 = mapper.toPost(post);
        post1.setTags(setTags(post1.getTags()));
        Post post2 = postRepository.save(post1);
        return mapper.toPostDto(post2);
//        return mapper.toPostDto(repository.save(mapper.toPost(post)));
    }

    private Set<Tag> setTags(Set<Tag> tags) {
        Set<Tag> tags2 = new HashSet<>();
        for (Tag tag: tags) {
            tags2.add(tagRepository.findByName(tag.getName()).orElseGet(() -> tagRepository.save(tag)));
        }
        return tags2;
    }

//    private PostDto updateDto(PostDto oldPost, PostDto newPost) {
//        if (!oldPost.getEmail().equalsIgnoreCase(newPost.getEmail())) {
//            throwIfEmailIsNotUnique(newPost.getEmail());
//        }
//        if (!oldPost.getName().equalsIgnoreCase(newPost.getName())) {
//            throwIfNameIsNotUnique(newPost.getName());
//        }
//        newPost.setId(oldPost.getId());
//        newPost.setPassword(oldPost.getPassword());
//        newPost.setRole(oldPost.getRole());
//        return mapper.toPostDto(repository.save(mapper.toPost(newPost)));
//    }

//    @Override
//    public PostDto update(Long id, @Valid PostDto post) {
//        return updateDto(getById(id), post);
//    }
//
//    @Override
//    public PostDto updateCurrentPost(@Valid PostDto post) {
//        return updateDto(getCurrentPost(), post);
//    }
//
//    @Override
//    public void delete(Long id) {
//        if (!repository.existsById(id)) {
//            throw new EntityNotFoundException(Const.USER_DOESNT_EXIST);
//        }
//        repository.deleteById(id);
//    }
}
