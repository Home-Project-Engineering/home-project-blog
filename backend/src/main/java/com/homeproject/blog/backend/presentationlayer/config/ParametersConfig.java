package com.homeproject.blog.backend.presentationlayer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@Configuration
public class ParametersConfig {

    public static PageRequest getSortParameters(Integer pageNum, Integer pageSize, String sort){

        if(sort.contains("-")){
            return PageRequest.of(pageNum, pageSize, Sort.by(sort.substring(1)).descending());
        }
        return PageRequest.of(pageNum, pageSize, Sort.by(sort));
    }
}
