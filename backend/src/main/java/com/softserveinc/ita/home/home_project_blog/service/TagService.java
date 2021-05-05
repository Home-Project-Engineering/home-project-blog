package com.softserveinc.ita.home.home_project_blog.service;

import com.softserveinc.ita.home.home_project_blog.repository.TagRepository;
import com.softserveinc.ita.home.home_project_blog.repository.entity.Tag;
import com.softserveinc.ita.home.home_project_blog.service.dto.TagDto;
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

@Validated
@RequiredArgsConstructor
@Service
public class TagService implements ITagService {

    private final TagRepository tagRepository;
    private final TagMapperService mapper;
    private final TagMapperService tagMapper;
    private final IUserService userService;

    @Override
    public Page<TagDto> findAll(Long id, String name, Integer pageNum, Integer pageSize, String sort) {
        Pageable paging = GeneralService.pagination(pageNum, pageSize, sort);
        Page<Tag> postsPage;
        if ((name != null) && (id != null)) {
            postsPage = tagRepository.findByNameAndId(name, id, paging);
        } else if (id != null) {
            postsPage = tagRepository.findById(id, paging);
        } else if (name != null) {
            postsPage = tagRepository.findByName(name, paging);
        } else {
            postsPage = tagRepository.findAll(paging);
        }
        return mapper.toPageTagDto(postsPage);
    }

    @Override
    public TagDto getById(Long id) {
        return mapper.toTagDto(tagRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(Const.TAG_DOESNT_EXIST)));
    }

    @Override
    public void delete(Long id) {
        if (!tagRepository.existsById(id)) {
            throw new EntityNotFoundException(Const.TAG_DOESNT_EXIST);
        }
        //TODO posts_tags verify
        tagRepository.deleteById(id);
    }
}
