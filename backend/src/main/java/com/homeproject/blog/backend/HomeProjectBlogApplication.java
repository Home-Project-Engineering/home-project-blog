package com.homeproject.blog.backend;

import com.homeproject.blog.backend.classes.Author;
import com.homeproject.blog.backend.classes.Post;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.util.LinkedHashMap;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class HomeProjectBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeProjectBlogApplication.class, args);


	}


}
