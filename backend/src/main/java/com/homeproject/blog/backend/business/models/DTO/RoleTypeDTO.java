package com.homeproject.blog.backend.business.models.DTO;

import com.homeproject.blog.backend.business.models.additional.Role;
import org.hibernate.boot.jaxb.cfg.spi.JaxbCfgCacheUsageEnum;
import org.hibernate.boot.jaxb.hbm.spi.JaxbHbmLazyEnum;

public class RoleTypeDTO {
    public static Role NameEnum;
    Long id;
    String name;

    public RoleTypeDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public RoleTypeDTO() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
