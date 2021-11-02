package com.homeproject.blog.backend.businesslayer.services;

import com.homeproject.blog.backend.businesslayer.dto.CommentDTO;
import com.homeproject.blog.backend.data.entities.Comment;
import com.homeproject.blog.backend.data.entities.User;
import com.homeproject.blog.backend.data.repositories.CommentRepository;
import com.homeproject.blog.backend.data.repositories.PostRepository;
import com.homeproject.blog.backend.exceptions.CommentNotFoundException;
import com.homeproject.blog.backend.exceptions.PostNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.OffsetDateTime;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserService userService;

    @Autowired
    ConversionService conversionService;

    @Override
    public CommentDTO createComment(Long postId, CommentDTO commentDTO) {
        if(postRepository.findById(postId).isEmpty()){
            throw new PostNotFoundException();
        }
        Comment comment = conversionService.convert(commentDTO, Comment.class);
        comment.setAuthor(userService.getCurrentUserByUsername());
        comment.setPost(postRepository.getById(postId));
        comment.setCreatedOn(OffsetDateTime.now());
        comment.setUpdatedOn(OffsetDateTime.now());
        commentRepository.save(comment);
        return conversionService.convert(comment, CommentDTO.class);
    }

    @Override
    public CommentDTO updateComment(Long postId, Long id, CommentDTO commentDTO) {
        if(postRepository.findById(postId).isEmpty()){
            throw new PostNotFoundException();
        }
        if(commentRepository.findById(id).isEmpty()){
            throw new CommentNotFoundException();
        }
        Comment comment = commentRepository.getById(id);
        comment.setText(commentDTO.getText());
        comment.setUpdatedOn(OffsetDateTime.now());
        commentRepository.save(comment);
        return conversionService.convert(comment, CommentDTO.class);
    }

    @Override
    public CommentDTO readComment(Long postId, Long id) {
        if(postRepository.findById(postId).isEmpty()){
            throw new PostNotFoundException();
        }
        if(commentRepository.findById(id).isEmpty()){
            throw new CommentNotFoundException();
        }
        return conversionService.convert(commentRepository.getById(id), CommentDTO.class);
    }

    @Override
    public Page<CommentDTO> getComments(Long postId, Long id, String authorName, Pageable pageRequest) {
        if(postRepository.findById(postId).isEmpty()){
            throw new PostNotFoundException();
        }
        Page<Comment> allByIdAndName = commentRepository.findAllByParameters(pageRequest, postId, id, authorName);
        return new PageImpl<>(allByIdAndName.stream().map(comment -> conversionService.convert(comment, CommentDTO.class)).collect(Collectors.toList()), pageRequest, allByIdAndName.getTotalElements());
    }

    @Override
    public void deleteComment(Long postId, Long id) {
        if(postRepository.findById(postId).isEmpty()){
            throw new PostNotFoundException();
        }
        if(commentRepository.findById(id).isEmpty()){
            throw new CommentNotFoundException();
        }
        commentRepository.deleteById(id);
    }

    @Override
    public CommentDTO getCommentByCurrentUser(Long id) {
        User user = userService.getCurrentUserByUsername();
        Comment comment = commentRepository.findByAuthorAndId(id, user.getId()).orElseThrow(CommentNotFoundException::new);
        return conversionService.convert(comment, CommentDTO.class);
    }

    @Override
    public Page<CommentDTO> getCommentsByCurrentUser(Long id, Pageable pageRequest) {
        User user = userService.getCurrentUserByUsername();
        Page<Comment> allByIdAndName = commentRepository.findByUserId(pageRequest,id, user.getId());
        return new PageImpl<>(allByIdAndName.stream().map(comment -> conversionService.convert(comment, CommentDTO.class)).collect(Collectors.toList()), pageRequest, allByIdAndName.getTotalElements());
    }

    @Override
    public CommentDTO updateCommentByCurrentUser(Long id, CommentDTO commentDTO) {
        User user = userService.getCurrentUserByUsername();
        Comment comment = commentRepository.findByAuthorAndId(id, user.getId()).orElseThrow(CommentNotFoundException::new);
        comment.setText(commentDTO.getText());
        comment.setUpdatedOn(OffsetDateTime.now());
        commentRepository.save(comment);
        return conversionService.convert(comment, CommentDTO.class);
    }

    @Override
    public void deleteCommentByCurrentUser(Long id) {
        User user = userService.getCurrentUserByUsername();
        Comment comment = commentRepository.findByAuthorAndId(id, user.getId()).orElseThrow(CommentNotFoundException::new);
        commentRepository.deleteById(comment.getId());
    }
}
