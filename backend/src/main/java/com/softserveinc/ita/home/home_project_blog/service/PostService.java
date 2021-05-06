package com.softserveinc.ita.home.home_project_blog.service;

import com.softserveinc.ita.home.home_project_blog.repository.PostRepository;
import com.softserveinc.ita.home.home_project_blog.repository.TagRepository;
import com.softserveinc.ita.home.home_project_blog.repository.entity.Post;
import com.softserveinc.ita.home.home_project_blog.repository.entity.Tag;
import com.softserveinc.ita.home.home_project_blog.service.dto.PostDto;
import com.softserveinc.ita.home.home_project_blog.service.dto.TagDto;
import com.softserveinc.ita.home.home_project_blog.service.mapper.PostMapperService;
import com.softserveinc.ita.home.home_project_blog.service.mapper.TagMapperService;
import com.softserveinc.ita.home.home_project_blog.validation.Const;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Validated
@RequiredArgsConstructor
@Service
public class PostService implements IPostService {

    private final PostRepository postRepository;
    private final TagRepository tagRepository;
    private final PostMapperService mapper;
    private final TagMapperService tagMapper;
    private final IUserService userService;

    @Override
    public Page<PostDto> findAll(Long id, Long tag_id, String tag_name, Long user_id, Integer pageNum, Integer pageSize, String sort) {
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

    Post getPostById(Long id){
        return postRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(Const.POST_DOESNT_EXIST));
    }

    @Override
    public PostDto getById(Long id) {
        return mapper.toPostDto(postRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(Const.POST_DOESNT_EXIST)));
    }

    @Override
    public PostDto save(@Valid PostDto post) {
        post.setUser(userService.getCurrentUser());
        post.setTags(updateTags(post.getTags()));
        return mapper.toPostDto(postRepository.save(mapper.toPost(post)));
    }

//    private Set<Tag> setTags(Set<Tag> tags) {
//        Set<Tag> tags2 = new HashSet<>();
//        for (Tag tag : tags) {
//            tags2.add(tagRepository.findByName(tag.getName()).orElseGet(() -> tagRepository.save(tag)));
//        }
//        return tags2;
//    }


//    private Set<TagDto> updateTags(Set<TagDto> tags) {
//        //Set<TagDto> tags2 = new HashSet<>();
//        for (TagDto tag: tags) {
//            if (tagRepository.existsByName(tag.getName())){
//                tag.setId(tagRepository.findByName(tag.getName()).get().getId());
//            }
////            tags2.add(tagRepository.findByName(tag.getName()).orElseGet(() -> tagRepository.save(tag)));
//        }
//        return tags2;
//    }

    private Set<TagDto> updateTags(Set<TagDto> tags) {
        Set<TagDto> tags2 = new HashSet<>();
        for (TagDto tag : tags) {
//            tags2.add(tagMapper.toTagDto(addTagIfNotExists(tag.getName())));
            tags2.add(tagMapper.toTagDto(
                    tagRepository.findByName(tag.getName())
                            .orElseGet(() -> tagRepository.save(tagMapper.toTag(tag)))
            ));
        }
        return tags2;
    }

//    private Tag addTagIfNotExists(String name){
//        return tagRepository.findByName(name)
//                        .orElseGet(() -> tagRepository.save(new Tag(null,name)));
//    }

    private void deleteTags(Set<Tag> tags) {
        for (Tag tag : tags) {
            deleteTag(tag.getId());
        }
    }


    //todo delete tag from posts_tags and maybe from tags
    private void deleteTag(Long id) {
        tagRepository.deleteById(id);
        //tagRepository.deleteById(tagRepository.findByName(tag.getName()));
    }

    private Set<TagDto> compareAndUpdateTags(Set<TagDto> oldTags, Set<TagDto> newTags){
        Set<String> stringTags = newTags.stream().map(TagDto::getName).collect(Collectors.toSet());
        Set<String> stringOldTags = oldTags.stream().map(TagDto::getName).collect(Collectors.toSet());
        if (stringOldTags.equals(stringTags)) {
            System.out.println("Tags weren't changed!!!");
            return oldTags;
        }
        Set<TagDto> mergeTags = updateTags(newTags);

        //delete extra tags
        for (TagDto tag: oldTags) {
            if (!stringTags.contains(tag.getName())) {
                deleteTag(tag.getId());
            }
        }
        return mergeTags;
    }

    private PostDto updateDto(PostDto oldPost, PostDto postUpdates) {
        oldPost.setTags(compareAndUpdateTags(oldPost.getTags(), postUpdates.getTags()));
        oldPost.setText(postUpdates.getText());
        oldPost.setTitle(postUpdates.getTitle());
        oldPost.setPreviewAttachment(postUpdates.getPreviewAttachment());
        return mapper.toPostDto(postRepository.save(mapper.toPost(oldPost)));
    }

    @Override
    public PostDto update(Long id, @Valid PostDto post) {
        return updateDto(getById(id), post);
    }

//    @Override
//    public PostDto updateCurrentPost(@Valid PostDto post) {
//        return updateDto(getCurrentPost(), post);
//    }

    @Override
    public void delete(Long id) {
        if (!postRepository.existsById(id)) {
            throw new EntityNotFoundException(Const.POST_DOESNT_EXIST);
        }
        deleteTags(postRepository.findById(id).get().getTags());
        postRepository.deleteById(id);
    }
}
