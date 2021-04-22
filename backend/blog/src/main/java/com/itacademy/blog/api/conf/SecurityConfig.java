package com.itacademy.blog.api.conf;

import com.itacademy.blog.model.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.GET, "/api/**").hasAnyRole(User.RoleEnum.USER.getValue(),
                                                                             User.RoleEnum.ADMIN.getValue(),
                                                                             User.RoleEnum.EXPERT.getValue(),
                                                                             User.RoleEnum.GUEST.getValue(),
                                                                             User.RoleEnum.MODERATOR.getValue())
                .antMatchers(HttpMethod.POST, "/api/**").hasAnyRole(
                                                                             User.RoleEnum.USER.getValue(),
                                                                             User.RoleEnum.ADMIN.getValue(),
                                                                             User.RoleEnum.EXPERT.getValue(),
                                                                             User.RoleEnum.MODERATOR.getValue())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    protected UserDetailsService userDetailsService() {
        return super.userDetailsService();
    }
    protected PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }
}
