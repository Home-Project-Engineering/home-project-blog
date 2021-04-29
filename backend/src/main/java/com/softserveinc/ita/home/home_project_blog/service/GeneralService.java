package com.softserveinc.ita.home.home_project_blog.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class GeneralService {
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
}
