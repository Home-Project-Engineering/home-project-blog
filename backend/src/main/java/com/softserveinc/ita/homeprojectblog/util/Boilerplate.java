package com.softserveinc.ita.homeprojectblog.util;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

@Component
public class Boilerplate {

    public MultiValueMap<String, String> getXTotalCount(Page<?> page) {
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("X-Total-Count", String.valueOf(page.getTotalElements()));
        return headers;
    }

}
