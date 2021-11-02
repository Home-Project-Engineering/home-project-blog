package com.homeproject.blog.backend.businesslayer;

import com.homeproject.blog.backend.data.entity.RoleTypeEntity;
import com.homeproject.blog.backend.data.repository.RoleTypeRepository;
import com.homeproject.blog.backend.dtos.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    private RoleTypeRepository roleTypeRepository;
    @Override
    public RoleType identify(RoleType roleType) {
        RoleTypeEntity roleTypeEntity = roleTypeRepository.findByName(roleType.getName());
        roleType.setId(roleTypeEntity.getId());
        return roleType;
    }
}
