package com.itacademy.blog.services.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    private BigDecimal id;

    private String name;

    private String firstName;

    private String lastName;

    private String email;

    private String password;


    private RoleDTO role;



}
