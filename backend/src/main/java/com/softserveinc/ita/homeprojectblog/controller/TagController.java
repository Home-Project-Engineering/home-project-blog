package com.softserveinc.ita.homeprojectblog.controller;

import com.softserveinc.ita.homeprojectblog.generated.api.TagsApi;
import com.softserveinc.ita.homeprojectblog.generated.model.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/1")
public class TagController implements TagsApi {

    @Override
    public ResponseEntity<Tag> getTag(BigDecimal id) {
        return null;
    }

    @Override
    public ResponseEntity<List<Tag>> getTags(BigDecimal id, String name, String sort, Integer pageNum, Integer pageSize) {
        return null;
    }

    @Override
    public ResponseEntity<Void> removeTag(BigDecimal id) {
        return null;
    }
}
