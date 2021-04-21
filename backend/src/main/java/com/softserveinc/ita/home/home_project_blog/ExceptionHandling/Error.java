package com.softserveinc.ita.home.home_project_blog.ExceptionHandling;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Error {
    String code;
    String message;
}
