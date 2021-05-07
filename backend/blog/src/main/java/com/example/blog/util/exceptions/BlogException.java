package com.example.blog.backend.util.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BlogException {
    private String code;
    private String message;
}
