/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.18).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package com.softserveinc.ita.homeprojectblog.generated.api;

import java.math.BigDecimal;
import com.softserveinc.ita.homeprojectblog.generated.model.Comment;
import com.softserveinc.ita.homeprojectblog.generated.model.Error;
import com.softserveinc.ita.homeprojectblog.generated.model.Post;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-04-08T10:34:14.997+03:00[Europe/Helsinki]")
@Api(value = "posts", description = "the posts API")
public interface PostsApi {

    @ApiOperation(value = "Create a Post", nickname = "create", notes = "", response = Post.class, authorizations = {
        @Authorization(value = "basicAuth")    }, tags={ "posts", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "OK", response = Post.class),
        @ApiResponse(code = 400, message = "The payload contains an error", response = Error.class),
        @ApiResponse(code = 200, message = "The unknown error appeard. Check your payload or contact support.", response = Error.class) })
    @RequestMapping(value = "/posts",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Post> create(@ApiParam(value = "some parameters" ,required=true )  @Valid @RequestBody Post body
);


    @ApiOperation(value = "Delete post by ID", nickname = "deleteComment", notes = "Delete a post", authorizations = {
        @Authorization(value = "basicAuth")    }, tags={ "comments", })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "The request was succesfully processed."),
        @ApiResponse(code = 400, message = "The payload contains an error", response = Error.class),
        @ApiResponse(code = 404, message = "The specified resource was not found", response = Error.class),
        @ApiResponse(code = 200, message = "The unknown error appeard. Check your payload or contact support.", response = Error.class) })
    @RequestMapping(value = "/posts/{post_id}/comments/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteComment(@ApiParam(value = "",required=true) @PathVariable("post_id") BigDecimal postId
,@ApiParam(value = "",required=true) @PathVariable("id") BigDecimal id
);


    @ApiOperation(value = "Delete post by ID", nickname = "deletePost", notes = "Delete a post", authorizations = {
        @Authorization(value = "basicAuth")    }, tags={ "posts", })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "The request was succesfully processed."),
        @ApiResponse(code = 400, message = "The payload contains an error", response = Error.class),
        @ApiResponse(code = 404, message = "The specified resource was not found", response = Error.class),
        @ApiResponse(code = 200, message = "The unknown error appeard. Check your payload or contact support.", response = Error.class) })
    @RequestMapping(value = "/posts/{post_id}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deletePost(@ApiParam(value = "",required=true) @PathVariable("id") BigDecimal id
);


    @ApiOperation(value = "This gets all comments to post", nickname = "getAllComments", notes = "", response = Comment.class, responseContainer = "List", authorizations = {
        @Authorization(value = "basicAuth")    }, tags={ "comments", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = Comment.class, responseContainer = "List"),
        @ApiResponse(code = 404, message = "The specified resource was not found", response = Error.class),
        @ApiResponse(code = 200, message = "The unknown error appeard. Check your payload or contact support.", response = Error.class) })
    @RequestMapping(value = "/posts/{post_id}/comments",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Comment>> getAllComments(@ApiParam(value = "",required=true) @PathVariable("post_id") BigDecimal postId
,@ApiParam(value = "Find by id") @Valid @RequestParam(value = "id", required = false) BigDecimal id
,@ApiParam(value = "Find by user name") @Valid @RequestParam(value = "user_name", required = false) String userName
,@ApiParam(value = "Find by user id") @Valid @RequestParam(value = "user_id", required = false) String userId
,@ApiParam(value = "In order to execute *asc*, you need to specify in the search *id* parameter. In order to complete the *desc* sorting must be specified in the query parameter *-id* ", allowableValues = "id, -id", defaultValue = "-id") @Valid @RequestParam(value = "sort", required = false, defaultValue="-id") String sort
,@ApiParam(value = "") @Valid @RequestParam(value = "page_num", required = false) Integer pageNum
,@ApiParam(value = "") @Valid @RequestParam(value = "page_size", required = false) Integer pageSize
);


    @ApiOperation(value = "This gets all posts", nickname = "getAllPosts", notes = "", response = Post.class, responseContainer = "List", authorizations = {
        @Authorization(value = "basicAuth")    }, tags={ "posts", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = Post.class, responseContainer = "List"),
        @ApiResponse(code = 404, message = "The specified resource was not found", response = Error.class),
        @ApiResponse(code = 200, message = "The unknown error appeard. Check your payload or contact support.", response = Error.class) })
    @RequestMapping(value = "/posts",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Post>> getAllPosts(@ApiParam(value = "Find by id") @Valid @RequestParam(value = "id", required = false) BigDecimal id
,@ApiParam(value = "Find by tag id") @Valid @RequestParam(value = "tag_id", required = false) String tagId
,@ApiParam(value = "Find by tag name") @Valid @RequestParam(value = "tag_name", required = false) String tagName
,@ApiParam(value = "Find by user id") @Valid @RequestParam(value = "user_id", required = false) String userId
,@ApiParam(value = "In order to execute *asc*, you need to specify in the search *id* or *title* parameter. In order to complete the *desc* sorting must be specified in the query parameter *-id* ", allowableValues = "id, -id, title, -title", defaultValue = "-id") @Valid @RequestParam(value = "sort", required = false, defaultValue="-id") String sort
,@ApiParam(value = "") @Valid @RequestParam(value = "page_num", required = false) Integer pageNum
,@ApiParam(value = "") @Valid @RequestParam(value = "page_size", required = false) Integer pageSize
);


    @ApiOperation(value = "Find comment by ID", nickname = "getCommentById", notes = "Returns a single comment", response = Comment.class, authorizations = {
        @Authorization(value = "basicAuth")    }, tags={ "comments", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = Comment.class),
        @ApiResponse(code = 400, message = "The payload contains an error", response = Error.class),
        @ApiResponse(code = 404, message = "The specified resource was not found", response = Error.class),
        @ApiResponse(code = 200, message = "The unknown error appeard. Check your payload or contact support.", response = Error.class) })
    @RequestMapping(value = "/posts/{post_id}/comments/{id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Comment> getCommentById(@ApiParam(value = "",required=true) @PathVariable("post_id") BigDecimal postId
,@ApiParam(value = "",required=true) @PathVariable("id") BigDecimal id
);


    @ApiOperation(value = "Find post by ID", nickname = "getPost", notes = "Returns a post", response = Post.class, authorizations = {
        @Authorization(value = "basicAuth")    }, tags={ "posts", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = Post.class),
        @ApiResponse(code = 400, message = "The payload contains an error", response = Error.class),
        @ApiResponse(code = 404, message = "The specified resource was not found", response = Error.class),
        @ApiResponse(code = 200, message = "The unknown error appeard. Check your payload or contact support.", response = Error.class) })
    @RequestMapping(value = "/posts/{post_id}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Post> getPost(@ApiParam(value = "",required=true) @PathVariable("id") BigDecimal id
);


    @ApiOperation(value = "Leave a Comment", nickname = "leaveAComment", notes = "", response = Comment.class, authorizations = {
        @Authorization(value = "basicAuth")    }, tags={ "comments", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "OK", response = Comment.class),
        @ApiResponse(code = 400, message = "The payload contains an error", response = Error.class),
        @ApiResponse(code = 200, message = "The unknown error appeard. Check your payload or contact support.", response = Error.class) })
    @RequestMapping(value = "/posts/{post_id}/comments",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Comment> leaveAComment(@ApiParam(value = "some parameters" ,required=true )  @Valid @RequestBody Comment body
,@ApiParam(value = "",required=true) @PathVariable("post_id") BigDecimal postId
);


    @ApiOperation(value = "Update commet by ID", nickname = "updateComment", notes = "Updates a comment", response = Comment.class, authorizations = {
        @Authorization(value = "basicAuth")    }, tags={ "comments", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = Comment.class),
        @ApiResponse(code = 400, message = "The payload contains an error", response = Error.class),
        @ApiResponse(code = 404, message = "The specified resource was not found", response = Error.class),
        @ApiResponse(code = 200, message = "The unknown error appeard. Check your payload or contact support.", response = Error.class) })
    @RequestMapping(value = "/posts/{post_id}/comments/{id}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<Comment> updateComment(@ApiParam(value = "some parameters" ,required=true )  @Valid @RequestBody Comment body
,@ApiParam(value = "",required=true) @PathVariable("post_id") BigDecimal postId
,@ApiParam(value = "",required=true) @PathVariable("id") BigDecimal id
);


    @ApiOperation(value = "Update post by ID", nickname = "updatePost", notes = "Updates a post", response = Post.class, authorizations = {
        @Authorization(value = "basicAuth")    }, tags={ "posts", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = Post.class),
        @ApiResponse(code = 400, message = "The payload contains an error", response = Error.class),
        @ApiResponse(code = 404, message = "The specified resource was not found", response = Error.class),
        @ApiResponse(code = 200, message = "The unknown error appeard. Check your payload or contact support.", response = Error.class) })
    @RequestMapping(value = "/posts/{post_id}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<Post> updatePost(@ApiParam(value = "some parameters" ,required=true )  @Valid @RequestBody Post body
,@ApiParam(value = "",required=true) @PathVariable("id") BigDecimal id
);

}