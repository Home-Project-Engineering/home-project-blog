package com.homeproject.blog.backend.presentationlayer.converters;

import com.homeproject.blog.backend.dtos.RoleType;
import com.homeproject.blog.backend.presentationlayer.model.Role;
import com.homeproject.blog.backend.presentationlayer.model.Tag;
import com.homeproject.blog.backend.presentationlayer.model.User;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service(value = "user_model_converter")
public class UserConverterImpl implements UserConverter{
    @Override
    public User dtoToModel(com.homeproject.blog.backend.dtos.User dto) {
        User model = new User();
        model.setId(new BigDecimal(dto.getId()));
        model.setName(dto.getName());
        model.setFirstName(dto.getFirstName());
        model.setLastName(dto.getLastName());
        model.setEmail(dto.getEmail());
        model.setPassword(dto.getPassword());
        Role role = new Role();
        role.setName(Role.NameEnum.fromValue(dto.getRole().getName()));
        model.setRole(role);
        return model;
    }

    @Override
    public com.homeproject.blog.backend.dtos.User modelToDto(User model) {
        com.homeproject.blog.backend.dtos.User dto = new com.homeproject.blog.backend.dtos.User();
        Long id = model.getId() == null ? null : model.getId().longValue();
        dto.setId(id);
        dto.setName(model.getName());
        dto.setFirstName(model.getFirstName());
        dto.setLastName(model.getLastName());
        dto.setEmail(model.getEmail());
        dto.setPassword(model.getPassword());
        String role = dto.getRole() == null ? "blogger" : dto.getRole().getName();
        dto.setRole(new RoleType(null, role));
        return dto;
    }

    @Override
    public List<User> dtosToViews(List<com.homeproject.blog.backend.dtos.User> toList) {
        Stream<User> stream = toList.stream().map(this::dtoToModel);
        return stream.collect(Collectors.toCollection(ArrayList::new));
    }
}
