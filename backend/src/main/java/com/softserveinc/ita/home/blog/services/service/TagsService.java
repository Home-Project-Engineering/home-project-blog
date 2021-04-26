package com.softserveinc.ita.home.blog.services.service;

import com.softserveinc.ita.home.blog.model.Error;
import com.softserveinc.ita.home.blog.model.Tag;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-04-15T11:28:34.593463600+03:00[Europe/Helsinki]")
@Api(value = "tags", description = "the tags API")
public interface TagsService {

    @ApiOperation(value = "Delete a tag by ID", nickname = "deleteTag", notes = "", authorizations = {
        @Authorization(value = "basicAuth")    }, tags={ "tags", })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "The request was succesfully processed."),
        @ApiResponse(code = 400, message = "The payload contains an error", response = Error.class),
        @ApiResponse(code = 404, message = "The specified resource was not found", response = Error.class),
        @ApiResponse(code = 200, message = "The unknown error appeard. Check your payload or contact support.", response = Error.class) })
    @RequestMapping(value = "/tags/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteTag(@ApiParam(value = "",required=true) @PathVariable("id") long id
);


    @ApiOperation(value = "", nickname = "getAllTags", notes = "", response = Tag.class, responseContainer = "List", authorizations = {
        @Authorization(value = "basicAuth")    }, tags={ "tags", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = Tag.class, responseContainer = "List"),
        @ApiResponse(code = 404, message = "The specified resource was not found", response = Error.class),
        @ApiResponse(code = 200, message = "The unknown error appeard. Check your payload or contact support.", response = Error.class) })
    @RequestMapping(value = "/tags",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Tag>> getAllTags(@ApiParam(value = "Find by id") @Valid @RequestParam(value = "id", required = false) long id
,@ApiParam(value = "Find by name") @Valid @RequestParam(value = "name", required = false) String name
,@ApiParam(value = "In order to execute *asc*, you need to specify in the search *id* or *name* parameter. In order to complete the *desc* sorting must be specified in the query parameter *-id* ", allowableValues = "id, -id, name, -name", defaultValue = "name") @Valid @RequestParam(value = "sort", required = false, defaultValue="name") String sort
,@ApiParam(value = "") @Valid @RequestParam(value = "page_num", required = false) Integer pageNum
,@ApiParam(value = "") @Valid @RequestParam(value = "page_size", required = false) Integer pageSize
);


    @ApiOperation(value = "Find tag by ID", nickname = "getTag", notes = "Returns a tag", response = Tag.class, authorizations = {
        @Authorization(value = "basicAuth")    }, tags={ "tags", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = Tag.class),
        @ApiResponse(code = 400, message = "The payload contains an error", response = Error.class),
        @ApiResponse(code = 404, message = "The specified resource was not found", response = Error.class),
        @ApiResponse(code = 200, message = "The unknown error appeard. Check your payload or contact support.", response = Error.class) })
    @RequestMapping(value = "/tags/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Tag> getTag(@ApiParam(value = "",required=true) @PathVariable("id") long id
);

}
