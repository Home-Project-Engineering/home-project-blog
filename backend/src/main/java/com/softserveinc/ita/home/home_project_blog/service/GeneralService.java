package com.softserveinc.ita.home.home_project_blog.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneralService<T> {
    public static Pageable pagination(Integer pageNum, Integer pageSize, String sortBy) {
        Pageable paging;
        if (sortBy.charAt(0) == '-') {
            paging = PageRequest.of(pageNum, pageSize, Sort.by(sortBy.substring(1)).descending());
        } else {
            if (sortBy.charAt(0) == '+') {
                sortBy = sortBy.substring(1);
            }
            paging = PageRequest.of(pageNum, pageSize, Sort.by(sortBy).ascending());
        }
        return paging;
    }

    public ResponseEntity<List<T>> toResponseEntity (Page<T> page) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("X-Total-Count",
                String.valueOf(page.getTotalElements()));
        return new ResponseEntity<>((List<T>) page.getContent(), responseHeaders, HttpStatus.OK);
    }
}
