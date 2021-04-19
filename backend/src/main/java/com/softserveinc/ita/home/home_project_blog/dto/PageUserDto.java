package com.softserveinc.ita.home.home_project_blog.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
public class PageUserDto {
    private List<UserDto> userDtos;
    private int totalPages;
    public PageUserDto(List<UserDto> userDtos, int totalPages){
        this.userDtos = userDtos;
        this.totalPages = totalPages;
    }

    public List<UserDto> getContent(){
        return userDtos;
    }

    public int getTotalPages(){
        return totalPages;
    }
}
