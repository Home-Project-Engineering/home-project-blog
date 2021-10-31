package com.homeproject.blog.backend.businesslayer;

import com.homeproject.blog.backend.dtos.RoleType;

public interface RoleService {

    RoleType identify(RoleType roleType);
}
