package com.homeproject.blog.backend.config;

import com.homeproject.blog.backend.businesslayer.converters.*;
import com.homeproject.blog.backend.presentationlayer.converters.BlogConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Set;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    Set<BlogDTOConverter> blogDTOConverters;

    @Autowired
    Set<BlogConverter> blogConverters;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        blogDTOConverters.stream().forEach(registry::addConverter);
        blogConverters.stream().forEach(registry::addConverter);
    }

}
