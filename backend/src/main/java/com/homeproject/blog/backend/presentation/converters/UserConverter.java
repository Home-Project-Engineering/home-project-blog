package com.homeproject.blog.backend.presentation.converters;

import com.homeproject.blog.backend.business.models.DTO.RoleTypeDTO;
import com.homeproject.blog.backend.business.models.DTO.UserDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service(value = "user_model_converter")
public class UserConverter {
    public UserDTO dtoToModel(com.homeproject.blog.backend.business.models.DTO.UserDTO dto) {
            UserDTO model = new UserDTO();
            model.setId(new Long(dto.getId()));
            model.setName(dto.getName());
            model.setFirstName(dto.getFirstName());
            model.setLastName(dto.getLastName());
            model.setEmail(dto.getEmail());
            model.setPassword(dto.getPassword());
            RoleTypeDTO role = new RoleTypeDTO();
            role.setName(RoleTypeDTO.NameEnum.fromValue(dto.getRole().getName()));
            model.setRole(role);
            return model;
        }
        public com.homeproject.blog.backend.business.models.DTO.UserDTO modelToDto(UserDTO model) {
            com.homeproject.blog.backend.business.models.DTO.UserDTO dto = new com.homeproject.blog.backend.business.models.DTO.UserDTO();
            Long id = model.getId() == null ? null : model.getId().longValue();
            dto.setId(id);
            dto.setName(model.getName());
            dto.setFirstName(model.getFirstName());
            dto.setLastName(model.getLastName());
            dto.setEmail(model.getEmail());
            dto.setPassword(model.getPassword());
            String role = dto.getRole() == null ? "blogger" : dto.getRole().getName();
            dto.setRole(new RoleTypeDTO(null, role));
            return dto;
        }

        public List<UserDTO> dtosToViews(List<com.homeproject.blog.backend.business.models.DTO.UserDTO> toList) {
            Stream<UserDTO> stream = toList.stream().map(this::dtoToModel);
            return stream.collect(Collectors.toCollection(ArrayList::new));
        }
}
