package com.homeproject.blog.backend.config.security;

import com.homeproject.blog.backend.data.entities.User;
import com.homeproject.blog.backend.data.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String name) {
        User user = userRepository.findByName(name).orElseThrow(() ->
                new UsernameNotFoundException("User" + name + " does not exists"));
        Set<SimpleGrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().getName().name()));
        return new org.springframework.security.core.userdetails.User(user.getName(),user.getPassword(),true,true,true, true, grantedAuthorities);
    }
}
