package com.example.blog.services;

import com.example.blog.repository.entities.CommentEntity;
import com.example.blog.repository.entities.PostEntity;
import com.example.blog.repository.entities.RoleEntity;
import com.example.blog.repository.entities.UserEntity;
import com.example.blog.repository.repos.CommentRepo;
import com.example.blog.repository.repos.PostRepo;
import com.example.blog.repository.repos.RoleRepo;
import com.example.blog.repository.repos.UserRepo;
import com.example.blog.util.dtos.DtoComment;
import com.example.blog.util.dtos.DtoPost;
import com.example.blog.util.dtos.DtoRole;
import com.example.blog.util.dtos.DtoUser;
import com.example.blog.util.mappers.CommentMapper;
import com.example.blog.util.mappers.PostMapper;
import com.example.blog.util.mappers.UserMapper;
import com.example.blog.util.specifications.CommentSpecification;
import com.example.blog.util.specifications.PostSpecification;
import com.example.blog.util.specifications.UserSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.validation.ValidationException;
import java.net.PasswordAuthentication;
import java.util.Optional;

@Service
public class UserService {

    private UserRepo userRepo;
    private UserSpecification userSpec;
    private PostSpecification postSpec;
    private CommentSpecification commentSpec;
    private PasswordEncoder passwordEncoder;
    private RoleRepo roleRepo;
    private PostRepo postRepo;
    private CommentRepo commentRepo;

    public UserService(UserRepo userRepo,
                       UserSpecification userSpec,
                       PostSpecification postSpec,
                       CommentSpecification commentSpec,
                       PasswordEncoder passwordEncoder,
                       RoleRepo roleRepo,
                       PostRepo postRepo,
                       CommentRepo commentRepo) {

        this.userRepo = userRepo;
        this.userSpec = userSpec;
        this.postSpec = postSpec;
        this.commentSpec = commentSpec;
        this.passwordEncoder = passwordEncoder;
        this.roleRepo = roleRepo;
        this.postRepo = postRepo;
        this.commentRepo = commentRepo;
    }

    public DtoUser createUser(DtoUser dtoUser) {
        UserEntity entity = UserMapper.INSTANCE.fromDto(dtoUser);
        entity.setPassword(passwordEncoder.encode(dtoUser.getPassword()));


        Optional<RoleEntity> roleEntity = Optional.ofNullable(roleRepo.findOneByName(RoleEntity.RoleEnum.BLOGGER));

        if (roleEntity.isPresent()) {
            entity.setRoleEntity(roleEntity.get());
        } else {
            RoleEntity re = new RoleEntity();
            re.setName(RoleEntity.RoleEnum.BLOGGER);

            entity.setRoleEntity(re);
        }

        userRepo.save(entity);
        return UserMapper.INSTANCE.fromEntity(entity);
    }


    public DtoUser getUser(Long id) {
        UserEntity entity = userRepo
                .findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id <" + id + "> is not exists"));

        return UserMapper.INSTANCE.fromEntity(entity);
    }


    public Page<DtoUser> getUsers(Long id, String name, String sort, Integer pageNum, Integer pageSize) {
        Specification<UserEntity> spec = userSpec.id(id).and(userSpec.name(name));

        Page<UserEntity> page;

        if (pageNum != null && pageSize != null) {
            page = userRepo.findAll(spec, PageRequest.of(pageNum, pageSize, getSorter(sort)));
        } else {
            page = userRepo.findAll(spec, PageRequest.of(0, 50, getSorter(sort)));
        }

        return UserMapper.INSTANCE.toPageDto(page);
    }

    public void removeUser(Long id) {
        if (userRepo.findById(id).isEmpty())
            throw new EntityNotFoundException("User with id <" + id + "> is not exists");
        userRepo.deleteById(id);
    }

    private Sort getSorter(String sort) {
        if (sort == null) {
            return Sort.by(Sort.Direction.DESC, "id");
        } else {
            if (sort.contains("-")) {
                String par = sort.substring(1);
                return Sort.by(Sort.Direction.DESC, par);

            } else {
                return Sort.by(Sort.Direction.ASC, sort);
            }
        }

    }

    public DtoUser updateUser(Long id, DtoUser dtoUser) {
        if (userRepo.findById(id).isEmpty())
            throw new EntityNotFoundException("User with id <" + id + "> is not exists");
        UserEntity entity = UserMapper.INSTANCE.fromDto(dtoUser);
        entity.setId(id);
        entity.setPassword(passwordEncoder.encode(dtoUser.getPassword()));
        entity.setRoleEntity(userRepo.findById(id).get().getRoleEntity());

        userRepo.save(entity);

        return UserMapper.INSTANCE.fromEntity(entity);
    }

    public DtoRole getUserRole(Long id) {
        if (userRepo.findById(id).isEmpty())
            throw new EntityNotFoundException("User with id <" + id + "> is not exists");

        return UserMapper.INSTANCE.convertToDto(userRepo.findById(id).get().getRoleEntity());
    }


    public DtoRole updateUserRole(Long id, DtoRole dtoRole) {

        if (userRepo.findById(id).isEmpty())
            throw new EntityNotFoundException("User with id <" + id + "> is not exists");

        RoleEntity roleEntity = UserMapper.INSTANCE.convertToEntity(dtoRole);

        UserEntity user = userRepo.findById(id).get();
        if (roleRepo.findOneByName(roleEntity.getName()) != null) {
            user.setRoleEntity(roleRepo.findOneByName(roleEntity.getName()));
        } else {
            user.setRoleEntity(roleEntity);
        }

        userRepo.save(user);

        RoleEntity forReturn = userRepo.findById(id).get().getRoleEntity();


        return UserMapper.INSTANCE.convertToDto(forReturn);
    }

    public UserEntity getCurrentUserEntity() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String email;
        if (principal instanceof UserDetails) {
            email = ((UserDetails) principal).getUsername();
        } else {
            email = principal.toString();
        }
        return userRepo
                .findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User with email <" + email + "> is not exists in BLOG"));
    }

    public DtoUser getCurrentUserDto() {
        return UserMapper.INSTANCE.fromEntity(getCurrentUserEntity());
    }

    public DtoUser updateCurrentUser(DtoUser dtoUser) {
        UserEntity entity = UserMapper.INSTANCE.fromDto(dtoUser);
        entity.setPassword(passwordEncoder.encode(dtoUser.getPassword()));
        entity.setRoleEntity(getCurrentUserEntity().getRoleEntity());
        entity.setId(getCurrentUserEntity().getId());
        userRepo.save(entity);

        return UserMapper.INSTANCE.fromEntity(entity);
    }

    public void removePostByCurrentUser(Long id) {
        Specification<PostEntity> specification =
                postSpec.userId(getCurrentUserEntity().getId().toString())
                        .and(postSpec.id(id));

        PostEntity postEntity = postRepo.findOne(specification)
                .orElseThrow(() -> new EntityNotFoundException("No Post with ID " + id));

        postRepo.delete(postEntity);
    }

    public void removeCommentByCurrentUser(Long id) {
        Specification<CommentEntity> specification =
                commentSpec.userId(getCurrentUserEntity().getId().toString())
                        .and(commentSpec.id(id));

        CommentEntity commentEntity = commentRepo.findOne(specification)
                .orElseThrow(() -> new EntityNotFoundException("No Comment with ID " + id));

        commentRepo.delete(commentEntity);
    }

    public DtoPost updatePostByCurrentUser(Long id, DtoPost postDto) {
        Specification<PostEntity> specification =
                postSpec.userId(getCurrentUserEntity().getId().toString())
                        .and(postSpec.id(id));

        PostEntity postEntity = postRepo.findOne(specification)
                .orElseThrow(() -> new EntityNotFoundException("No Post with ID " + id));

        postEntity = PostMapper.INSTANCE.toEntity(postDto);
        postEntity.setId(id);
        postRepo.save(postEntity);

        return PostMapper.INSTANCE.toDtoFromEntity(postEntity);
    }

    public DtoComment updateCommentByCurrentUser(Long id, DtoComment dtoComment) {
        Specification<CommentEntity> specification =
                commentSpec.userId(getCurrentUserEntity().getId().toString())
                        .and(commentSpec.id(id));

        CommentEntity commentEntity = commentRepo.findOne(specification)
                .orElseThrow(() -> new EntityNotFoundException("No Comment with ID " + id));

        commentEntity = CommentMapper.INSTANCE.toEntity(dtoComment);
        commentEntity.setId(id);
        commentRepo.save(commentEntity);

        return CommentMapper.INSTANCE.toDtoFromEntity(commentEntity);
    }

    public void updateCurrentUserPassword(String newPassword, String oldPassword) {

        UserEntity userEntity = getCurrentUserEntity();
        if (newPassword != null && passwordEncoder.matches(oldPassword, userEntity.getPassword())) {
            userEntity.setPassword(passwordEncoder.encode(newPassword));
        } else {
            throw new ValidationException("Wrong password");
        }
        userRepo.save(userEntity);
    }

    public DtoPost getPostByCurrentUser(Long id) {
        Specification<PostEntity> specification =
                postSpec.userId(getCurrentUserEntity().getId().toString())
                        .and(postSpec.id(id));

        PostEntity postEntity = postRepo.findOne(specification)
                .orElseThrow(() -> new EntityNotFoundException("No Post with ID " + id));

        return PostMapper.INSTANCE.toDtoFromEntity(postEntity);
    }

    public Page<DtoPost> getPostsByCurrentUser(Long id, String tagId, String tagName, String sort, Integer pageNum, Integer pageSize) {
        Specification<PostEntity> spec = postSpec.id(id)
                .and(postSpec.tagId(tagId))
                .and(postSpec.tagName(tagName));

        Page<PostEntity> page;

        if (pageNum != null && pageSize != null) {
            page = postRepo.findAll(spec, PageRequest.of(pageNum, pageSize, getSorter(sort)));
        } else {
            page = postRepo.findAll(spec, PageRequest.of(0, 50, getSorter(sort)));
        }
        return PostMapper.INSTANCE.toDtoPage(page);
    }
}
