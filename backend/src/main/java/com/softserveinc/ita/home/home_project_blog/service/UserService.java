package com.softserveinc.ita.home.home_project_blog.service;

import com.softserveinc.ita.home.home_project_blog.Validation.EmailIsNotUniqueException;
import com.softserveinc.ita.home.home_project_blog.Validation.NameIsNotUniqueException;
import com.softserveinc.ita.home.home_project_blog.Validation.Const;
import com.softserveinc.ita.home.home_project_blog.repository.entity.*;
import com.softserveinc.ita.home.home_project_blog.repository.UserRepository;
import com.softserveinc.ita.home.home_project_blog.service.dto.UserDto;
import com.softserveinc.ita.home.home_project_blog.service.mapper.UserMapperService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @Override
    public Pageable pagination(Integer pageNum, Integer pageSize, String sortBy) {
        Pageable paging;
        if (sortBy.charAt(0) == '-') {
            paging = PageRequest.of(pageNum, pageSize, Sort.by(sortBy.substring(1)).descending());
        } else {
            if (sortBy.charAt(0) == '+') {
                sortBy = sortBy.substring(1);
            }
            paging = PageRequest.of(pageNum, pageSize, Sort.by(sortBy).ascending());
        }
        return paging;
    }

    @Override
    public Page<UserDto> findAll(Integer pageNum, Integer pageSize, String sortBy) {
        return mapper.toPageUserDto(repository.findAll(pagination(pageNum, pageSize, sortBy)));
    }

    @Override
    public Page<UserDto> findAll(Pageable paging) {
        return mapper.toPageUserDto(repository.findAll(paging));
    }

    @Override
    public UserDto getById(Long id) {
        return mapper.toUserDto(repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format(Const.NOT_FOUND_USER_BY_ID, id))));
    }

    @Override
    public UserDto save(@Valid UserDto user) {
        user.setRole(ROLE.user);
        throwIfEmailIsNotUnique(user.getEmail());
        throwIfNameIsNotUnique(user.getName());
        return mapper.toUserDto(repository.save(mapper.toUser(user)));
    }

    private void throwIfEmailIsNotUnique(String email) {
        if (repository.existsByEmail(email)) {
            throw new EmailIsNotUniqueException();
        }
    }

    private void throwIfNameIsNotUnique(String name) {
        if (repository.existsByName(name)) {
            throw new NameIsNotUniqueException();
        }
    }

    @Override
    public UserDto update(Long id, @Valid UserDto user) {
        user.setId(id);
        UserDto oldUser = getById(id);
        if (!oldUser.getEmail().equalsIgnoreCase(user.getEmail())) {
            throwIfEmailIsNotUnique(user.getEmail());
        }
        if (!oldUser.getName().equalsIgnoreCase(user.getName())) {
            throwIfNameIsNotUnique(user.getName());
        }
        user.setPassword(oldUser.getPassword());
        user.setRole(oldUser.getRole());
        return mapper.toUserDto(repository.save(mapper.toUser(user)));
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException(String.format(Const.NOT_FOUND_USER_BY_ID, id));
        }
        repository.deleteById(id);
    }

    @Override
    public Page<UserDto> getByName(String name, Integer pageNum, Integer pageSize, String sortBy) {
        return mapper.toPageUserDto(repository.findByName(name, pagination(pageNum, pageSize, sortBy)));
    }

    @Override
    public Page<UserDto> getByName(String name, Pageable paging) {
        return mapper.toPageUserDto(repository.findByName(name, paging));
    }

    @Override
    public Page<UserDto> getById(Long id, Pageable paging) {
        return mapper.toPageUserDto(repository.findById(id, paging));
    }

    @Override
    public Page<UserDto> getByNameAndId(String name, Long id, Pageable paging) {
        return mapper.toPageUserDto(repository.findByNameAndId(name, id, paging));
    }
}
