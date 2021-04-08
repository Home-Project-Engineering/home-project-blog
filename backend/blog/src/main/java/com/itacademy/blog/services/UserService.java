package com.itacademy.blog.services;


import com.itacademy.blog.repository.Entity.UserEntity;
import com.itacademy.blog.repository.UserRepo;
import com.itacademy.blog.services.DTO.UserDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService {

    UserRepo userRepo;
    Converter converter;

    public UserService(UserRepo userRepo, Converter converter) {
        this.userRepo = userRepo;
        this.converter = converter;
    }



    public List<UserDTO> getAllEntities(int pageNum, int pageSize) {
        List<UserEntity> entities = userRepo.findAll(PageRequest.of(pageNum, pageSize)).toList();
        return converter.convert(entities);
    }
    public List<UserDTO> getAllEntities(int pageNum, int pageSize, Long id) {
        List<UserEntity> entities = userRepo.findAllById(id, PageRequest.of(pageNum, pageSize));
        return converter.convert(entities);
    }
    public List<UserDTO> getAllEntities(int pageNum, int pageSize, String name) {
        List<UserEntity> entities = userRepo.findAllByName(name, PageRequest.of(pageNum, pageSize));
        return converter.convert(entities);
    }
    public List<UserDTO> getAllEntities(int pageNum, int pageSize, String name, Long id) {
        List<UserEntity> entities = userRepo.findAllByNameAndId(name, id, PageRequest.of(pageNum, pageSize));
        return converter.convert(entities);
    }
    public List<UserDTO> getAllEntitiesSorted(int pageNum, int pageSize, String sort) {
        StringBuilder str = new StringBuilder(sort);
        List<UserEntity> entities;
        if(str.charAt(0) == '-'){
            str.deleteCharAt(0);
            entities = userRepo.findAll(PageRequest.of(pageNum, pageSize, Sort.by(sort).descending())).toList();
        }
        else {
            entities = userRepo.findAll(PageRequest.of(pageNum, pageSize, Sort.by(sort))).toList();
        }
        return converter.convert(entities);
    }
    public List<UserDTO> getAllEntities(int pageNum, int pageSize, Long id, String sort) {
        StringBuilder str = new StringBuilder(sort);
        List<UserEntity> entities;
        if(str.charAt(0) == '-'){
            str.deleteCharAt(0);
            entities = userRepo.findAllById(id, PageRequest.of(pageNum, pageSize, Sort.by(sort).descending()));
        }
        else {
            entities = userRepo.findAllById(id, PageRequest.of(pageNum, pageSize, Sort.by(sort).ascending()));
        }
        return converter.convert(entities);
    }

    public List<UserDTO> getAllEntities(int pageNum, int pageSize, String name, String sort) {
        StringBuilder str = new StringBuilder(sort);
        List<UserEntity> entities;
        if(str.charAt(0) == '-'){
            str.deleteCharAt(0);
            entities = userRepo.findAllByName(name, PageRequest.of(pageNum, pageSize, Sort.by(sort).descending()));
        }
        else {
            entities = userRepo.findAllByName(name, PageRequest.of(pageNum, pageSize, Sort.by(sort).ascending()));
        }
        return converter.convert(entities);
    }
    public List<UserDTO> getAllEntities(int pageNum, int pageSize, String name, Long id, String sort) {
        StringBuilder str = new StringBuilder(sort);
        List<UserEntity> entities;
        if(str.charAt(0) == '-'){
            str.deleteCharAt(0);
            entities = userRepo.findAllByNameAndId(name, id, PageRequest.of(pageNum, pageSize, Sort.by(sort).descending()));
        }
        else {
            entities = userRepo.findAllByNameAndId(name, id, PageRequest.of(pageNum, pageSize, Sort.by(sort).ascending()));
        }
        return converter.convert(entities);
    }

}
