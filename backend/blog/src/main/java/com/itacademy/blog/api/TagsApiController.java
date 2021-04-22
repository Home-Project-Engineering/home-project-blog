package com.itacademy.blog.api;

import com.itacademy.blog.api.mapper.TagMapper;
import com.itacademy.blog.model.Tag;
import com.itacademy.blog.services.DTO.TagDTO;
import com.itacademy.blog.services.query.EntitySpecificationService;
import com.itacademy.blog.services.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.List;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-04-22T13:30:27.722673500+03:00[Europe/Kiev]")
@Controller
@RequestMapping("${openapi.homeProjectBlogService.base-path:/api/1}")
public class TagsApiController implements TagsApi {

    private final NativeWebRequest request;
    private final TagService tagService;
    private final EntitySpecificationService entitySpecificationService;

    @Autowired
    public TagsApiController(NativeWebRequest request, TagService tagService, EntitySpecificationService entitySpecificationService) {
        this.request = request;
        this.tagService = tagService;
        this.entitySpecificationService = entitySpecificationService;
    }


    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Void> removeTag(BigDecimal id) {
        Optional<TagDTO> optionalTagDTO = Optional.ofNullable(tagService.deleteTag(id.longValue()));

        if (optionalTagDTO.isPresent()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        //to do return tag model in response
        Tag returnTag = TagMapper.INSTANCE.convert(optionalTagDTO.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<List<Tag>> getTags(@Valid BigDecimal id, @Valid String name, @Valid String sort, @Valid Integer pageNum, @Valid Integer pageSize) {
        Map<String, String> filterMap = new HashMap<>();

        if (id != null) {
            filterMap.put("id", id.toString());
        } else {
            filterMap.put("id", null);
        }
        filterMap.put("name", name);

        List<Tag> tags = tagService.findAllTags(pageNum
                , pageSize, sort
                , entitySpecificationService.getSpecification(filterMap));


        return tags.isEmpty() ? new ResponseEntity<>(HttpStatus.BAD_REQUEST) : new ResponseEntity<List<Tag>>(tags, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<Tag> getTag(BigDecimal id) {
        TagDTO readTagDto = tagService.getTagById(id.longValue());

        Tag returnTag = TagMapper.INSTANCE.convert(readTagDto);

        return new ResponseEntity<>(returnTag, HttpStatus.OK);

    }

}
