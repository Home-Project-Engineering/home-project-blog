package com.softserveinc.ita.home.home_project_blog.service;

import com.softserveinc.ita.home.home_project_blog.repository.CommentRepository;
import com.softserveinc.ita.home.home_project_blog.repository.entity.Comment;
import com.softserveinc.ita.home.home_project_blog.service.dto.CommentDto;
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
