package com.softserveinc.ita.home.home_project_blog.service;

import com.softserveinc.ita.home.home_project_blog.dto.CreateUserDto;
import com.softserveinc.ita.home.home_project_blog.dto.UserDto;
import com.softserveinc.ita.home.home_project_blog.mappers.UserMapper;
import com.softserveinc.ita.home.home_project_blog.models.User;
import com.softserveinc.ita.home.home_project_blog.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

//@RequiredArgsConstructor
@Service
public class UserService implements IUserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    public UserService(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

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
    public Optional<UserDto> getById(Long id) {
        return mapper.toOptionalUserDto(repository.findById(id));
    }

    @Override
    public User save(CreateUserDto user) {
//        if (repository.existsById(user.getId())) {
//            user.setId(-1L);
//        }
        return repository.save(mapper.signUpToUser(user));
    }

    @Override
    public Optional<UserDto> update(Long id, CreateUserDto user) {
        //return repository.findById(id).map(value -> repository.save(value)).orElseThrow();

        if (repository.existsById(id)) {
            return Optional.of(mapper.toUserDto(repository.save(mapper.signUpToUser(user))));
        }
        return Optional.empty();
    }

    @Override
    public boolean delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;//throw?
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
