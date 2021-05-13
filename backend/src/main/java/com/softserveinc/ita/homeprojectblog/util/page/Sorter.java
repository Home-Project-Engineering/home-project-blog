package com.softserveinc.ita.homeprojectblog.util.page;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class Sorter {
    public Sort getSorter(String sort) {
        var str = new StringBuilder(sort);

        if (str.charAt(0) == '-') {
            str.deleteCharAt(0);
            return Sort.by(Sort.Direction.DESC, str.toString());
        }
        return Sort.by(Sort.Direction.ASC, str.toString());
    }
}
