package com.example.blog.util.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BlogException {
    private String code;
    private String message;
}
