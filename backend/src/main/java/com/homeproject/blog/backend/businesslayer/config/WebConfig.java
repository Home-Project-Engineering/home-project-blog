package com.homeproject.blog.backend.businesslayer.config;

import com.homeproject.blog.backend.businesslayer.converters.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new PostDTOToPostConverter());
        registry.addConverter(new PostToPostDTOConverter());
        registry.addConverter(new TagToTagDTOConverter());
        registry.addConverter(new TagDTOToTagConverter());
        registry.addConverter(new UserToUserDTOConverter());
    }
}
