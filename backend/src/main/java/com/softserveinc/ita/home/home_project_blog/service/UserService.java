package com.softserveinc.ita.home.home_project_blog.service;

import com.softserveinc.ita.home.home_project_blog.repository.UserRepository;
import com.softserveinc.ita.home.home_project_blog.repository.entity.User;
import com.softserveinc.ita.home.home_project_blog.security.model.Role;
import com.softserveinc.ita.home.home_project_blog.service.dto.ChangePasswordBody;
import com.softserveinc.ita.home.home_project_blog.service.dto.UserDto;
import com.softserveinc.ita.home.home_project_blog.service.mapper.UserMapperService;
import com.softserveinc.ita.home.home_project_blog.specification.SpecificationService;
import com.softserveinc.ita.home.home_project_blog.validation.Const;
import com.softserveinc.ita.home.home_project_blog.validation.NotAuthorizedException;
import com.softserveinc.ita.home.home_project_blog.validation.NotUniqueException;
import com.softserveinc.ita.home.home_project_blog.validation.WrongPasswordException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Validated
@RequiredArgsConstructor
@Service
public class UserService implements IUserService {

    private final UserRepository repository;
    private final UserMapperService mapper;
    private final PasswordEncoder passwordEncoder;
    private final SpecificationService<User> userSpecificationService;

    @Override
    public Page<UserDto> findAll(Long id, String name, Integer pageNum, Integer pageSize, String sort) {
        Pageable paging = GeneralService.pagination(pageNum, pageSize, sort);
        Map<String, String> filter = new HashMap<>();
        filter.put("id", id != null ? id.toString() : null);
        filter.put("name", name);
        Specification<User> specification = userSpecificationService.getSpecification(filter);
        Page<User> pageUser = repository.findAll(specification, paging);
        return mapper.toPageUserDto(pageUser);
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

    @Override
    public Role getRole(Long id){
        return getById(id).getRole();
    }

    @Override
    public Role updateRole(Long id, @Valid Role role) {
        UserDto user = getById(id);
        user.setRole(role);
        repository.save(mapper.toUser(user));
        return getById(id).getRole();
    }

    private UserDto getByEmail(String email) {
        return mapper.toUserDto(repository.findByEmail(email).orElseThrow(
                () -> new EntityNotFoundException(Const.USER_DOESNT_EXIST)));
    }

    @Override
    public UserDto getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            throw new NotAuthorizedException();
        }
        return getByEmail(authentication.getName());
    }

    @Override
    public UserDto updateCurrentUser(@Valid UserDto user) {
        return update(getCurrentUser(), user);
    }

    @Override
    public void updateCurrentUserPassword(@Valid ChangePasswordBody changePassword) {
        UserDto user = getCurrentUser();
        if (!passwordEncoder.matches(changePassword.getOldPassword(),user.getPassword())){
            throw new WrongPasswordException();
        }
        user.setPassword(passwordEncoder.encode(changePassword.getNewPassword()));
        repository.save(mapper.toUser(user));
    }
}
