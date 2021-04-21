package com.softserveinc.ita.home.home_project_blog.service;

import com.softserveinc.ita.home.home_project_blog.ExceptionHandling.ErrorConst;
import com.softserveinc.ita.home.home_project_blog.dto.CreateUserDto;
import com.softserveinc.ita.home.home_project_blog.dto.UserDto;
import com.softserveinc.ita.home.home_project_blog.mappers.UserMapper;
import com.softserveinc.ita.home.home_project_blog.models.User;
import com.softserveinc.ita.home.home_project_blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.Optional;

@Validated
@RequiredArgsConstructor
@Service
public class UserService implements IUserService {

    private final UserRepository repository;

    @Override
    public Pageable pagination(Integer pageNum, Integer pageSize, String sortBy) {
        Pageable paging;
        if (sortBy.charAt(0) == '-') {
            paging = PageRequest.of(pageNum, pageSize, Sort.by(sortBy.substring(1)).descending());
        } else {
            if (sortBy.charAt(0) == '+') {
                sortBy = sortBy.substring(1);
            }
            //TODO exception if not id and not null
            paging = PageRequest.of(pageNum, pageSize, Sort.by(sortBy).ascending());
        }
        return paging;
    }

    @Override
//    public List<User> findAll() {
//        return (List<User>) repository.findAll();
//    }
    public Page<User> findAll(Integer pageNum, Integer pageSize, String sortBy) {
        return repository.findAll(pagination(pageNum, pageSize, sortBy));
//        PageUserDto pageUserDto = new PageUserDto(mapper.toUserDtos(pageUser.getContent()),pageUser.getTotalPages());
//        return pageUserDto;//mapper.toPageUserDto(repository.findAll(paging));
    }

    @Override
    public Page<User> findAll(Pageable paging) {
        return repository.findAll(paging);
    }

    @Override
    public User getById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format(ErrorConst.NOT_FOUND_USER_BY_ID, id)));
    }

    @Override
    public User save(@Valid User user) {
//        user.setRole(User.ROLE.user);
        return repository.save(user);
    }

    @Override
    public User update(Long id,@Valid User user) {
        user.setId(id);
        User oldUser = getById(id);
        user.setPassword(oldUser.getPassword());
        user.setRole(oldUser.getRole());
        return repository.save(user);
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException(String.format(ErrorConst.NOT_FOUND_USER_BY_ID, id));
        }
        repository.deleteById(id);
    }

    @Override
    public Page<User> getByName(String name, Integer pageNum, Integer pageSize, String sortBy) {
        return repository.findByName(name, pagination(pageNum, pageSize, sortBy));
    }

    @Override
    public Page<User> getByName(String name, Pageable paging) {
        return repository.findByName(name, paging);
    }

    @Override
    public Page<User> getById(Long id, Pageable paging) {
        return repository.findById(id, paging);
    }

    @Override
    public Page<User> getByNameAndId(String name, Long id, Pageable paging) {
        return repository.findByNameAndId(name, id, paging);
    }
}
