package com.homeproject.blog.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class HomeProjectBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeProjectBlogApplication.class, args);
	}

}
