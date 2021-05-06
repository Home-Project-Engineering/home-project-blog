package com.softserveinc.ita.home.home_project_blog.service;

import com.softserveinc.ita.home.home_project_blog.repository.UserRepository;
import com.softserveinc.ita.home.home_project_blog.security.model.Role;
import com.softserveinc.ita.home.home_project_blog.repository.entity.User;
import com.softserveinc.ita.home.home_project_blog.service.dto.UserDto;
import com.softserveinc.ita.home.home_project_blog.service.mapper.UserMapperService;
import com.softserveinc.ita.home.home_project_blog.validation.Const;
import com.softserveinc.ita.home.home_project_blog.validation.NotUniqueException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@Validated
@RequiredArgsConstructor
@Service
public class UserService implements IUserService {

    private final UserRepository repository;
    private final UserMapperService mapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Page<UserDto> findAll(Long id, String name, Integer pageNum, Integer pageSize, String sort) {
        Pageable paging = GeneralService.pagination(pageNum, pageSize, sort);
        Page<User> usersPage;
        if ((name != null) && (id != null)) {
            usersPage = repository.findByNameAndId(name, id, paging);
        } else if (name != null) {
            usersPage = repository.findByName(name, paging);
        } else if (id != null) {
            usersPage = repository.findById(id, paging);
        } else {
            usersPage = repository.findAll(paging);
        }
        return mapper.toPageUserDto(usersPage);
    }

    @Override
    public UserDto getById(Long id) {
        return mapper.toUserDto(repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(Const.USER_DOESNT_EXIST)));
    }

    private void throwIfEmailIsNotUnique(String email) {
        if (repository.existsByEmail(email)) {
            throw new NotUniqueException(Const.EMAIL_IS_NOT_UNIQUE);
        }
    }

    private void throwIfNameIsNotUnique(String name) {
        if (repository.existsByName(name)) {
            throw new NotUniqueException(Const.NAME_IS_NOT_UNIQUE);
        }
    }

    @Override
    public UserDto save(@Valid UserDto user) {
        throwIfEmailIsNotUnique(user.getEmail());
        throwIfNameIsNotUnique(user.getName());
        user.setRole(Role.BLOGGER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return mapper.toUserDto(repository.save(mapper.toUser(user)));
    }

    @Override
    public UserDto update(@Valid UserDto oldUser, @Valid UserDto newUser) {
        if (!oldUser.getEmail().equalsIgnoreCase(newUser.getEmail())) {
            throwIfEmailIsNotUnique(newUser.getEmail());
        }
        if (!oldUser.getName().equalsIgnoreCase(newUser.getName())) {
            throwIfNameIsNotUnique(newUser.getName());
        }
        newUser.setId(oldUser.getId());
        newUser.setPassword(oldUser.getPassword());
        newUser.setRole(oldUser.getRole());
        return mapper.toUserDto(repository.save(mapper.toUser(newUser)));
    }

    @Override
    public UserDto update(Long id, @Valid UserDto user) {
        return update(getById(id), user);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException(Const.USER_DOESNT_EXIST);
        }
        repository.deleteById(id);
    }
}
