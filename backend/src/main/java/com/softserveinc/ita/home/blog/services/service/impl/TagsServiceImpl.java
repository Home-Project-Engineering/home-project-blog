package com.softserveinc.ita.home.blog.services.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserveinc.ita.home.blog.services.service.TagsService;
import com.softserveinc.ita.home.blog.model.Tag;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-04-15T11:28:34.593463600+03:00[Europe/Helsinki]")
@Controller
public class TagsServiceImpl implements TagsService {

    private static final Logger log = LoggerFactory.getLogger(TagsServiceImpl.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public TagsServiceImpl(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> deleteTag(@ApiParam(value = "",required=true) @PathVariable("id") long id
) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Tag>> getAllTags(@ApiParam(value = "Find by id") @Valid @RequestParam(value = "id", required = false) long id
,@ApiParam(value = "Find by name") @Valid @RequestParam(value = "name", required = false) String name
,@ApiParam(value = "In order to execute *asc*, you need to specify in the search *id* or *name* parameter. In order to complete the *desc* sorting must be specified in the query parameter *-id* ", allowableValues = "id, -id, name, -name", defaultValue = "name") @Valid @RequestParam(value = "sort", required = false, defaultValue="name") String sort
,@ApiParam(value = "") @Valid @RequestParam(value = "page_num", required = false) Integer pageNum
,@ApiParam(value = "") @Valid @RequestParam(value = "page_size", required = false) Integer pageSize
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Tag>>(objectMapper.readValue("[ {\r\n  \"name\" : \"name\",\r\n  \"id\" : 6.027456183070403\r\n}, {\r\n  \"name\" : \"name\",\r\n  \"id\" : 6.027456183070403\r\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Tag>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Tag>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Tag> getTag(@ApiParam(value = "",required=true) @PathVariable("id") long id
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Tag>(objectMapper.readValue("{\r\n  \"name\" : \"name\",\r\n  \"id\" : 6.027456183070403\r\n}", Tag.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Tag>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Tag>(HttpStatus.NOT_IMPLEMENTED);
    }

}
