package com.softserveinc.ita.home.home_project_blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication//(exclude = {SecurityAutoConfiguration.class})
public class HomeProjectBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeProjectBlogApplication.class, args);
	}

}
