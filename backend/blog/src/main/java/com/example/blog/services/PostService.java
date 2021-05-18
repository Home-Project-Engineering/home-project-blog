package com.example.blog.services;


import com.example.blog.repository.entities.CommentEntity;
import com.example.blog.repository.entities.PostEntity;
import com.example.blog.repository.repos.CommentRepo;
import com.example.blog.repository.repos.PostRepo;
import com.example.blog.repository.repos.TagRepo;
import com.example.blog.util.dtos.DtoComment;
import com.example.blog.util.dtos.DtoPost;
import com.example.blog.util.dtos.DtoTag;
import com.example.blog.util.mappers.CommentMapper;
import com.example.blog.util.mappers.PostMapper;
import com.example.blog.util.specifications.CommentSpecification;
import com.example.blog.util.specifications.PostSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.validation.ValidationException;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PostService {
    private PostRepo postRepo;
    private PostSpecification postSpecification;
    private CommentSpecification commentSpecification;
    private CommentRepo commentRepo;
    private TagRepo tagRepo;
    private UserService userService;

    public PostService(PostRepo postRepo,
                       PostSpecification postSpecification,
                       CommentSpecification commentSpecification,
                       CommentRepo commentRepo,
                       UserService userService,
                       TagRepo tagRepo) {
        this.postRepo = postRepo;
        this.postSpecification = postSpecification;
        this.commentSpecification = commentSpecification;
        this.commentRepo = commentRepo;
        this.userService = userService;
        this.tagRepo = tagRepo;
    }

    public DtoPost createPost(DtoPost dtoPost) {

        if (!checkTagUniqueness(dtoPost.getTags())) {
            throw new ValidationException("Tags must be unique");
        }
        PostEntity entity = PostMapper.INSTANCE.toEntity(dtoPost);
        entity.getTags().removeIf((tag -> tagRepo.findByName(tag.getName()).isPresent()));

        for (DtoTag tag : dtoPost.getTags()) {
            if (tagRepo.findByName(tag.getName()).isPresent()) {
                entity.getTags().add(tagRepo.findByName(tag.getName()).get());
            }
        }
        entity.setAuthor(userService.getCurrentUserEntity());
        entity.setCreatedOn(OffsetDateTime.now());

        postRepo.save(entity);

        return PostMapper.INSTANCE.toDtoFromEntity(entity);
    }

    public DtoComment createComment(Long id, DtoComment dtoComment) {
        if (!postRepo.existsById(id))
            throw new EntityNotFoundException("No Post with ID " + id);

        CommentEntity entity = CommentMapper.INSTANCE.toEntity(dtoComment);
        entity.setAuthor(userService.getCurrentUserEntity());
        entity.setPost(postRepo.getOne(id));
        entity.setCreatedOn(OffsetDateTime.now());
        commentRepo.save(entity);

        return CommentMapper.INSTANCE.toDtoFromEntity(entity);
    }

    public DtoComment getComment(Long postId, Long id) {
        if (!postRepo.existsById(postId))
            throw new EntityNotFoundException("No Post with ID " + postId);
        if (!commentRepo.existsById(id))
            throw new EntityNotFoundException("No Comment with ID " + id);

        return CommentMapper.INSTANCE.toDtoFromEntity(commentRepo.findById(id).get());

    }

    public DtoPost getPost(Long id) {
        if (!postRepo.existsById(id))
            throw new EntityNotFoundException("No Post with ID " + id);
        PostEntity entity = postRepo.findById(id).get();

        return PostMapper.INSTANCE.toDtoFromEntity(entity);
    }

    public void removeComment(Long postId, Long id) {
        if (!postRepo.existsById(postId))
            throw new EntityNotFoundException("No Post with ID " + postId);
        if (!commentRepo.existsById(id))
            throw new EntityNotFoundException("No Comment with ID " + id);

        commentRepo.deleteById(id);
    }

    public void removePost(Long postId) {
        if (!postRepo.existsById(postId))
            throw new EntityNotFoundException("No Post with ID " + postId);

        postRepo.deleteById(postId);
    }

    public DtoPost updatePost(Long id, DtoPost dtoPost) {
        if (!postRepo.existsById(id))
            throw new EntityNotFoundException("No Post with ID " + id);

        if (!checkTagUniqueness(dtoPost.getTags())) {
            throw new ValidationException("Tags must be unique");
        }

        PostEntity entity = PostMapper.INSTANCE.toEntity(dtoPost);
        entity.getTags().removeIf((tag -> tagRepo.findByName(tag.getName()).isPresent()));

        for (DtoTag tag : dtoPost.getTags()) {
            if (tagRepo.findByName(tag.getName()).isPresent()) {
                entity.getTags().add(tagRepo.findByName(tag.getName()).get());
            }
        }
        entity.setId(id);
        entity.setUpdatedOn(OffsetDateTime.now());
        entity.setCreatedOn(postRepo.findById(id).get().getCreatedOn());
        entity.setAuthor(userService.getCurrentUserEntity());

        postRepo.save(entity);

        return PostMapper.INSTANCE.toDtoFromEntity(entity);

    }

    public Page<DtoPost> getPosts(Long id, String tagId, String tagName, String userId, String sort, Integer pageNum, Integer pageSize) {

        Specification<PostEntity> specification =
                postSpecification.id(id)
                        .and(postSpecification.tagId(tagId)
                                .and(postSpecification.userId(userId)
                                        .and(postSpecification.tagName(tagName))));

        Page<PostEntity> page;
        if (pageNum != null && pageSize != null) {
            page = postRepo.findAll(specification, PageRequest.of(pageNum - 1, pageSize, getSorter(sort)));
        } else {
            page = postRepo.findAll(specification, PageRequest.of(0, 50, getSorter(sort)));
        }
        return PostMapper.INSTANCE.toDtoPage(page);
    }

    public Page<DtoComment> getAllComments(Long postId, Long id, String authorName, String sort, Integer pageNum, Integer pageSize) {
        if (!postRepo.existsById(postId))
            throw new EntityNotFoundException("No Post with ID " + postId);

        Specification<CommentEntity> specification =
                commentSpecification.id(id)
                        .and(commentSpecification.postId(postId.toString()))
                        .and(commentSpecification.userName(authorName));

        Page<CommentEntity> page;
        if (pageNum != null && pageSize != null) {
            page = commentRepo.findAll(specification, PageRequest.of(pageNum - 1, pageSize, getSorter(sort)));
        } else {
            page = commentRepo.findAll(specification, PageRequest.of(0, 50, getSorter(sort)));
        }

        return CommentMapper.INSTANCE.toPageDto(page);
    }

    public DtoComment updateComment(Long postId, Long id, DtoComment dtoComment) {
        if (!postRepo.existsById(postId))
            throw new EntityNotFoundException("No Post with ID " + postId);
        if (!commentRepo.existsById(id))
            throw new EntityNotFoundException("No Comment with ID " + id);

        CommentEntity entity = commentRepo.getOne(id);
        if (dtoComment.getText() == null) {
            entity.setText("");
        } else {
            entity.setText(dtoComment.getText());
        }
        entity.setId(id);
        entity.setUpdatedOn(OffsetDateTime.now());
        entity.setCreatedOn(commentRepo.findById(id).get().getCreatedOn());

        return CommentMapper.INSTANCE.toDtoFromEntity(commentRepo.save(entity));
    }

    private Sort getSorter(String sort) {
        if (sort == null) {
            return Sort.by(Sort.Direction.DESC, "-id");
        } else {
            if (sort.contains("-")) {
                String par = sort.substring(1);
                return Sort.by(Sort.Direction.DESC, par);

            } else {
                return Sort.by(Sort.Direction.ASC, sort);
            }
        }

    }

    public DtoComment getCommentByCurrentUser(Long id) {
        if (!commentRepo.existsById(id))
            throw new EntityNotFoundException("No Comment with ID " + id);

        Specification<CommentEntity> specification = commentSpecification.id(id)
                .and(commentSpecification.userId(userService.getCurrentUserEntity().getId().toString()));

        CommentEntity comment = commentRepo.findAll(specification).get(0);

        return CommentMapper.INSTANCE.toDtoFromEntity(comment);
    }

    public Page<DtoComment> getCommentsByCurrentUser(Long id, String sort, Integer pageNum, Integer pageSize) {
        Specification<CommentEntity> specification =
                commentSpecification.id(id)
                        .and(commentSpecification.userId(userService.getCurrentUserEntity().getId().toString()));

        Page<CommentEntity> page;
        if (pageNum != null && pageSize != null) {
            page = commentRepo.findAll(specification, PageRequest.of(pageNum - 1, pageSize, getSorter(sort)));
        } else {
            page = commentRepo.findAll(specification, PageRequest.of(0, 50, getSorter(sort)));
        }

        return CommentMapper.INSTANCE.toPageDto(page);

    }

    private boolean checkTagUniqueness(List<DtoTag> tagList) {
        Set<String> tagNames = new HashSet<>();
        for (DtoTag tag : tagList) {
            tagNames.add(tag.getName());
        }
        return tagNames.size() == tagList.size();
    }


}
