package com.softserveinc.ita.home.home_project_blog.Validation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Violation {
    private String fieldName;
    private String message;
}