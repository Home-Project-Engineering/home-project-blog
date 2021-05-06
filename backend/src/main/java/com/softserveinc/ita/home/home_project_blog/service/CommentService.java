package com.softserveinc.ita.home.home_project_blog.service;

import com.softserveinc.ita.home.home_project_blog.repository.CommentRepository;
import com.softserveinc.ita.home.home_project_blog.repository.entity.Comment;
import com.softserveinc.ita.home.home_project_blog.service.dto.CommentDto;
import com.softserveinc.ita.home.home_project_blog.service.mapper.CommentMapperService;
import com.softserveinc.ita.home.home_project_blog.validation.MismatchException;
import com.softserveinc.ita.home.home_project_blog.validation.Const;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@Validated
@RequiredArgsConstructor
@Service
public class CommentService implements ICommentService {

    private final PostService postService;
    private final CommentRepository commentRepository;
    private final CommentMapperService mapper;
    private final IUserService userService;

    @Override
    public Page<CommentDto> findAll(Long post_id, Long id, String user_name, Long user_id, Integer pageNum, Integer pageSize, String sort) {
        Pageable paging = GeneralService.pagination(pageNum, pageSize, sort);
        Page<Comment> commentsPage;
//        if ((title != null) && (id != null)) {
//            commentsPage = repository.findByTitleAndId(title, id, paging);
//        } else if (title != null) {
//            commentsPage = repository.findByTitle(title, paging);
//        } else
        if (id != null) {
            commentsPage = commentRepository.findById(id, paging);
        } else {
            commentsPage = commentRepository.findAll(paging);
        }
        return mapper.toPageCommentDto(commentsPage);
    }

    @Override
    public CommentDto getById(Long post_id, Long id) {
        CommentDto comment = mapper.toCommentDto(commentRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(Const.COMMENT_DOESNT_EXIST)));
        //todo equals comments!!!! Does it work?
        if (!comment.getPost().equals(postService.getById(post_id))) {
            throw new MismatchException(Const.COMMENT_DOESNT_ADHERE_TO_THE_POST);
        }
        return comment;
    }

    @Override
    public CommentDto createComment(Long post_id, @Valid CommentDto comment) {
        comment.setPost(postService.getById(post_id));
        comment.setUser(userService.getCurrentUser());
        return mapper.toCommentDto(commentRepository.save(mapper.toComment(comment)));
    }

    @Override
    public CommentDto update(Long post_id, Long id, @Valid CommentDto comment) {
        CommentDto oldComment = getById(post_id, id);
        oldComment.setText(comment.getText());
        return mapper.toCommentDto(commentRepository.save(mapper.toComment(oldComment)));
    }

//    @Override
//    public CommentDto updateCurrentComment(@Valid CommentDto comment) {
//        return updateDto(getCurrentComment(), comment);
//    }

    @Override
    public void delete(Long post_id, Long id) {
        getById(post_id, id);
        commentRepository.deleteById(id);
    }
}
