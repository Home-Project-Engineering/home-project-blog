package com.homeproject.blog.backend.presentationlayer.controllers;

import com.homeproject.blog.backend.businesslayer.dto.TagDTO;
import com.homeproject.blog.backend.businesslayer.services.TagService;
import com.homeproject.blog.backend.presentationlayer.config.ParametersConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TagController {

   @Autowired
   private TagService tagService;

//    @GetMapping(produces = "application/json")
//    public ResponseEntity<Object> getAllTags(@RequestParam( name = "id", required = false) Long id, @RequestParam(name = "name", required = false) String name, @RequestParam(name = "sort", required = false, defaultValue = "name") String sort, @RequestParam(name = "page_num", required = false, defaultValue = "0") Integer pageNum, @RequestParam(name = "page_size", required = false, defaultValue = "10") Integer pageSize) {
//        Page<TagDTO> page = tagService.getTags(id, name, ParametersConfig.getSortParameters(pageNum, pageSize, sort));
//        return ResponseEntity.ok().header("X-Total-Count", String.valueOf(page.getTotalElements())).body(page.toList());
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Object> getTagById(@PathVariable Long id) {
//        return ResponseEntity.ok(tagService.getTag(id));
//    }
//
//    @DeleteMapping("/{id}")
//    public void removePost(@PathVariable Long id){
//        tagService.deleteTag(id);
//    }
}
