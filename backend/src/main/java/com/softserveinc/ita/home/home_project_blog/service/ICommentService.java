package com.softserveinc.ita.home.home_project_blog.service;

import com.softserveinc.ita.home.home_project_blog.service.dto.CommentDto;
import com.softserveinc.ita.home.home_project_blog.service.dto.PostDto;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Validated
public interface ICommentService {
    Page<CommentDto> findAll(Long post_id, Long comment_id, String user_name, Long user_id, Integer pageNum, Integer pageSize, String sort);

    CommentDto getById(Long post_id, Long comment_id);

    CommentDto createComment(Long post_id, @Valid CommentDto comment);

    CommentDto update(Long post_id, Long comment_id, @Valid CommentDto comment);

    void delete(Long post_id, Long comment_id);

    CommentDto getCommentByIdByCurrentUser(Long comment_id);

    CommentDto updateCommentByCurrentUser(Long comment_id, @Valid CommentDto comment);

    void deleteCommentByCurrentUser(Long comment_id);
}
