package com.softserveinc.ita.home.home_project_blog.service;

import com.softserveinc.ita.home.home_project_blog.service.dto.CommentDto;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Validated
public interface ICommentService {
    Page<CommentDto> findAll(Long post_id, Long id, String user_name, Long user_id, Integer pageNum, Integer pageSize, String sort);

    CommentDto getById(Long post_id, Long id);

    CommentDto createComment(Long post_id, @Valid CommentDto comment);

    CommentDto update(Long post_id, Long id, @Valid CommentDto comment);

    void delete(Long post_id, Long id);
}
