package com.homeproject.blog.backend.business.services.impl;

import com.homeproject.blog.backend.business.models.DTO.RoleTypeDTO;
import com.homeproject.blog.backend.business.services.RoleService;
import com.homeproject.blog.backend.database.repositories.RoleTypeRepository;
import com.homeproject.blog.backend.persistence.entity.RoleTypeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleTypeRepository roleTypeRepository;

    @Override
    public RoleTypeDTO identify(RoleTypeDTO roleType) {

        RoleTypeEntity roleTypeEntity = roleTypeRepository.findByName(roleType.getName());
        roleType.setId(roleTypeEntity.getId());
        return roleType;
    }
}
