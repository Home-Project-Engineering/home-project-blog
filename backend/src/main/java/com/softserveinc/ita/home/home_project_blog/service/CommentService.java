package com.softserveinc.ita.home.home_project_blog.service;

import com.softserveinc.ita.home.home_project_blog.repository.CommentRepository;
import com.softserveinc.ita.home.home_project_blog.repository.entity.Comment;
import com.softserveinc.ita.home.home_project_blog.service.dto.CommentDto;
import com.softserveinc.ita.home.home_project_blog.service.dto.PostDto;
import com.softserveinc.ita.home.home_project_blog.service.mapper.CommentMapperService;
import com.softserveinc.ita.home.home_project_blog.specification.SpecificationService;
import com.softserveinc.ita.home.home_project_blog.validation.MismatchException;
import com.softserveinc.ita.home.home_project_blog.validation.Const;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Validated
@RequiredArgsConstructor
@Service
public class CommentService implements ICommentService {

    private final PostService postService;
    private final CommentRepository commentRepository;
    private final CommentMapperService mapper;
    private final IUserService userService;
    private final SpecificationService<Comment> commentSpecificationService;

    @Override
    public Page<CommentDto> findAll(Long post_id, Long id, String user_name, Long user_id, Integer pageNum, Integer pageSize, String sort) {
        Pageable paging = GeneralService.pagination(pageNum, pageSize, sort);
        Map<String, String> filter = new HashMap<>();
        filter.put("id", id!=null?id.toString():null);
        filter.put("post.id", post_id!=null?post_id.toString():null);
        filter.put("user.name", user_name);
        filter.put("user.id", user_id!=null?user_id.toString():null);
        Specification<Comment> specification = commentSpecificationService.getSpecification(filter);
        Page<Comment> pageComment = commentRepository.findAll(specification, paging);
        return mapper.toPageCommentDto(pageComment);
    }

    private CommentDto getById(Long comment_id){
        return mapper.toCommentDto(commentRepository.findById(comment_id).orElseThrow(
                () -> new EntityNotFoundException(Const.COMMENT_DOESNT_EXIST)));
    }

    @Override
    public CommentDto getById(Long post_id, Long comment_id) {
        CommentDto comment = getById(comment_id);
        if (!comment.getPost().equals(postService.getById(post_id))) {
            throw new MismatchException(Const.COMMENT_DOESNT_ADHERE_TO_THE_POST);
        }
        return comment;
    }

    @Override
    public CommentDto createComment(Long post_id, @Valid CommentDto comment) {
        comment.setPost(postService.getById(post_id));
        comment.setAuthor(userService.getCurrentUser());
        return mapper.toCommentDto(commentRepository.save(mapper.toComment(comment)));
    }

    private CommentDto update(@Valid CommentDto oldComment, @Valid CommentDto newComment) {
        oldComment.setText(newComment.getText());
        return mapper.toCommentDto(commentRepository.save(mapper.toComment(oldComment)));
    }

    @Override
    public CommentDto update(Long post_id, Long comment_id, @Valid CommentDto comment) {
        return update(getById(post_id,comment_id), comment);
    }

    @Override
    public void delete(Long post_id, Long id) {
        getById(post_id, id);
        commentRepository.deleteById(id);
    }

    //***************CURRENT USER*************************

    @Override
    public CommentDto getCommentByIdByCurrentUser(Long comment_id) {
        CommentDto comment = getById(comment_id);
        if (!comment.getAuthor().equals(userService.getCurrentUser())) {
            throw new MismatchException(Const.COMMENT_DOESNT_ADHERE_TO_THE_USER);
        }
        return comment;
    }

    @Override
    public CommentDto updateCommentByCurrentUser(Long comment_id, @Valid CommentDto comment) {
        return update(getCommentByIdByCurrentUser(comment_id), comment);
    }

    @Override
    public void deleteCommentByCurrentUser(Long comment_id) {
        getCommentByIdByCurrentUser(comment_id);
        commentRepository.deleteById(comment_id);
    }
}
