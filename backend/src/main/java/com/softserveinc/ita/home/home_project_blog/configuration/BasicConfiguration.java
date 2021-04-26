package com.softserveinc.ita.home.home_project_blog.configuration;

import com.softserveinc.ita.home.home_project_blog.repository.entity.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class BasicConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.POST, "/api/0/users").permitAll() //hasAnyAuthority() //or ANY?
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
                User.builder()
                        .username("admin")
                        .password(passwordEncoder().encode("admin"))
                        .authorities(Role.ADMIN.getAuthorities())
                        .build(),
                User.builder()
                        .username("user")
                        .password(passwordEncoder().encode("user"))
                        .authorities(Role.ANY.getAuthorities())
                        .build(),
                User.builder()
                        .username("blogger")
                        .password(passwordEncoder().encode("blogger"))
                        .authorities(Role.BLOGGER.getAuthorities())
                        .build(),
                User.builder()
                        .username("moderator")
                        .password(passwordEncoder().encode("moderator"))
                        .authorities(Role.MODERATOR.getAuthorities())
                        .build()
        );
    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }


//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        PasswordEncoder encoder =
//                PasswordEncoderFactories.createDelegatingPasswordEncoder();
//
//        HttpSecurity http;
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/").permitAll()
//                .antMatchers(HttpMethod.GET,"/api/**").hasAnyRole(Role.ADMIN.name(), Role.USER.name())
//                .antMatchers(HttpMethod.POST,"/api/**").hasRole(Role.ADMIN.name())
//                .antMatchers(HttpMethod.DELETE,"/api/**").hasAnyRole(Role.ADMIN.name())
//                .anyRequest()
//                .authenticated()
//                .and()
//                .httpBasic();
//
//        auth
//                .inMemoryAuthentication()
//                .withUser("user")
//                .password(encoder.encode("password"))
//                .roles("USER")
//                .and()
//                .withUser("admin")
//                .password(encoder.encode("admin"))
//                .roles("USER", "ADMIN");
//    }
}
