package com.softserveinc.ita.homeprojectblog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDtoGet {

    private BigDecimal id;

    private String name;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private OffsetDateTime createOn;

    private RoleDto role;

    private Byte roleByte;
}
