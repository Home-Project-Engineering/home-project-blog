package com.softserveinc.ita.home.home_project_blog.service;

import com.softserveinc.ita.home.home_project_blog.repository.PostRepository;
import com.softserveinc.ita.home.home_project_blog.repository.TagRepository;
import com.softserveinc.ita.home.home_project_blog.repository.UserRepository;
import com.softserveinc.ita.home.home_project_blog.repository.entity.Post;
import com.softserveinc.ita.home.home_project_blog.service.dto.PostDto;
import com.softserveinc.ita.home.home_project_blog.service.dto.UserDto;
import com.softserveinc.ita.home.home_project_blog.service.mapper.PostMapperService;
import com.softserveinc.ita.home.home_project_blog.service.mapper.TagMapperService;
import com.softserveinc.ita.home.home_project_blog.service.mapper.UserMapperService;
import com.softserveinc.ita.home.home_project_blog.validation.MismatchException;
import com.softserveinc.ita.home.home_project_blog.validation.Const;
import com.softserveinc.ita.home.home_project_blog.validation.NotAuthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@Validated
@RequiredArgsConstructor
@Service
public class CurrentUserService implements ICurrentUserService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostMapperService postMapper;
    private final UserMapperService userMapper;
    private final IUserService userService;
    private final IPostService postService;

    private UserDto getByEmail(String email) {
        return userMapper.toUserDto(userRepository.findByEmail(email).orElseThrow(
                () -> new EntityNotFoundException(Const.USER_DOESNT_EXIST)));
    }
    @Override
    public UserDto getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            throw new NotAuthorizedException();
        }
        return getByEmail(authentication.getName());
    }

    @Override
    public UserDto updateCurrentUser(@Valid UserDto user) {
        return userService.update(getCurrentUser(), user);
    }


    @Override
    public Page<PostDto> getPosts(Long id, Long tag_id, String tag_name, Integer pageNum, Integer pageSize, String sort) {
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
            //todo find by user
            postsPage = postRepository.findAll(paging);
        }
        return postMapper.toPagePostDto(postsPage);
    }

//    Post getPostById(Long id){
//        return postRepository.findById(id).orElseThrow(
//                () -> new EntityNotFoundException(Const.POST_DOESNT_EXIST));
//    }

    @Override
    public PostDto getPostById(Long id) {
        PostDto post = postMapper.toPostDto(postRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(Const.POST_DOESNT_EXIST)));
        if (!post.getUser().equals(getCurrentUser())){
            throw new MismatchException(Const.POST_DOESNT_ADHERE_TO_THE_USER);
        }
        return post;
    }

//    private PostDto updateDto(PostDto oldPost, PostDto postUpdates) {
//        oldPost.setTags(compareAndUpdateTags(oldPost.getTags(), postUpdates.getTags()));
//        oldPost.setText(postUpdates.getText());
//        oldPost.setTitle(postUpdates.getTitle());
//        oldPost.setPreviewAttachment(postUpdates.getPreviewAttachment());
//        return mapper.toPostDto(postRepository.save(mapper.toPost(oldPost)));
//    }

    @Override
    public PostDto updatePost(Long post_id, @Valid PostDto post) {
        return postService.update(getPostById(post_id), post);
    }

////    @Override
////    public PostDto updateCurrentPost(@Valid PostDto post) {
////        return updateDto(getCurrentPost(), post);
////    }

    @Override
    public void deletePost(Long id) {
        getPostById(id);
        postRepository.deleteById(id);
    }
}
