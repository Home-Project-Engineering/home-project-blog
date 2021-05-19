package com.example.blog.util.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DtoUser {

    private BigDecimal id;

    private String name;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private DtoRole role;
}
