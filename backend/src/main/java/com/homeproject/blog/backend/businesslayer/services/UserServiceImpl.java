package com.homeproject.blog.backend.businesslayer.services;

import com.homeproject.blog.backend.businesslayer.dto.ChangePasswordDTO;
import com.homeproject.blog.backend.businesslayer.dto.PostDTO;
import com.homeproject.blog.backend.businesslayer.dto.RoleDTO;
import com.homeproject.blog.backend.businesslayer.dto.UserDTO;
import com.homeproject.blog.backend.data.entities.Post;
import com.homeproject.blog.backend.data.entities.Role;
import com.homeproject.blog.backend.data.entities.User;
import com.homeproject.blog.backend.data.repositories.RoleRepository;
import com.homeproject.blog.backend.data.repositories.UserRepository;
import com.homeproject.blog.backend.exceptions.RoleNotFoundException;
import com.homeproject.blog.backend.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ConversionService conversionService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDTO getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        return conversionService.convert(user, UserDTO.class);
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = conversionService.convert(userDTO, User.class);
        if(userRepository.findByName(user.getName()).isPresent()){
            throw new ValidationException("Username already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = roleRepository.findByName(Role.RoleName.BLOGGER)
                .orElse(new Role(null, Role.RoleName.BLOGGER));
        user.setRole(role);
        userRepository.save(user);
        return conversionService.convert(user, UserDTO.class);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, Long id) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        if(userRepository.findByName(userDTO.getName()).isPresent()){
            throw new ValidationException("Username already exists");
        }
        user.setName(userDTO.getName());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return conversionService.convert(user, UserDTO.class);
    }

    @Override
    public void removeUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        userRepository.deleteById(user.getId());
    }

    @Override
    public UserDTO getCurrentUser() {
        return conversionService.convert(getCurrentUserByUsername(), UserDTO.class);
    }

    @Override
    public Page<UserDTO> getUsers(Long id, String name, Pageable pageRequest) {
        Page<User> allByIdAndName = userRepository.findAllByIdAndAndName(pageRequest, id,name);
        return new PageImpl<>(allByIdAndName.stream().map(user -> conversionService.convert(user, UserDTO.class)).collect(Collectors.toList()), pageRequest, allByIdAndName.getTotalElements());
    }

    @Override
    public UserDTO updateCurrentUser(UserDTO userDTO) {
        User user = getCurrentUserByUsername();
        if(userRepository.findByName(userDTO.getName()).isPresent()){
            throw new ValidationException("Username already exists");
        }
        user.setName(userDTO.getName());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return conversionService.convert(user, UserDTO.class);
    }

    @Override
    public RoleDTO getUserRole(Long id) {
        UserDTO userDTO = getUser(id);
        return Optional.of(userDTO.getRole()).orElseThrow(RoleNotFoundException::new);
    }

    @Override
    public void updateCurrentUserPassword(ChangePasswordDTO changePasswordDto) {
        User user = getCurrentUserByUsername();
        if (passwordEncoder.matches(changePasswordDto.getOldPassword(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(changePasswordDto.getNewPassword()));
        } else {
            throw new ValidationException("Incorrect old password");
        }
        userRepository.save(user);
    }

    @Override
    public RoleDTO updateUserRole(Long id, RoleDTO roleDTO) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        Role role = roleRepository.findByName(Role.RoleName.valueOf(roleDTO.getName())).orElseThrow(RoleNotFoundException::new);
        user.setRole(role);
        userRepository.save(user);
        return conversionService.convert(user.getRole(), RoleDTO.class);
    }

    @Override
    public User getCurrentUserByUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            User user = userRepository.findByName(currentUserName).orElseThrow(() ->
                    new UsernameNotFoundException("User does not exists"));
            return userRepository.getById(user.getId());
        }
        throw new UserNotFoundException();
    }
}
