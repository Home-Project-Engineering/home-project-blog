package com.homeproject.blog.backend.businesslayer;

import com.homeproject.blog.backend.businesslayer.converters.UserConverter;
import com.homeproject.blog.backend.data.entity.RoleTypeEntity;
import com.homeproject.blog.backend.data.entity.UserEntity;
import com.homeproject.blog.backend.data.repository.UserRepository;
import com.homeproject.blog.backend.dtos.RoleType;
import com.homeproject.blog.backend.dtos.User;
import com.homeproject.blog.backend.exceptions.UserExistsException;
import com.homeproject.blog.backend.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private RoleService roleService;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public User save(User user) {
        UserEntity userEntity = userRepository.findUserByName(user.getName());
        if (userEntity != null) {
            throw new UserExistsException();
        }
        user.setRole(roleService.identify(user.getRole()));
        user.setPassword(encoder.encode(user.getPassword()));
        UserEntity entity = userConverter.userToEntity(user);
        return userConverter.entityToUser(userRepository.save(entity));
    }

    @Override
    public User findByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findUserByName(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException(username);
        }
        return userConverter.entityToUser(userEntity);
    }

    @Override
    public User findById(Long id) throws UsernameNotFoundException {
        Optional<UserEntity> entity = userRepository.findById(id);
        if (entity.isEmpty()) {
            throw new UserNotFoundException();
        }
        return userConverter.entityToUser(entity.get());
    }

    @Override
    public Page<User> findAll(Long id, String name, Integer pageNum, Integer pageSize, String sort) {
        Sort sorting;
        if (sort != null) {
            if (sort.charAt(0) == '-') {
                sorting = Sort.by(sort.substring(1)).descending();
            } else {
                sorting = Sort.by(sort);
            }
        } else {
            sorting = Sort.by("id").descending();
        }
        pageNum = pageNum == null ? pageNum = 0 : pageNum;
        pageSize = pageSize == null ? pageSize = 10 : pageSize;
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize, sorting);
        Page<UserEntity> allByIdAndName = userRepository.findAllByIdAndName(pageRequest, id, name);
        Page<User> page = new PageImpl<>(allByIdAndName.stream().map(userConverter::entityToUser).collect(Collectors.toList()), pageRequest, allByIdAndName.getTotalElements());
        return page;
    }

    @Override
    public void deleteUser(Long id) {
        Optional<UserEntity> entity = userRepository.findById(id);
        if (entity.isEmpty()) {
            throw new UserNotFoundException();
        }
        userRepository.deleteById(id);
    }

    @Override
    public User updateUser(Long id, User user) {
        User dto = findById(id);
        dto.setName(user.getName());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setPassword(encoder.encode(user.getPassword()));
        UserEntity entity = userRepository.save(userConverter.userToEntity(dto));
        return userConverter.entityToUser(entity);
    }

    @Override
    public RoleType getUserRole(Long id) {
        User user = findById(id);
        return user.getRole();
    }

    @Override
    public RoleType updateUserRole(Long id, RoleType role) {
        User user = findById(id);
        user.setRole(roleService.identify(role));
        save(user);
        return role;
    }
}
