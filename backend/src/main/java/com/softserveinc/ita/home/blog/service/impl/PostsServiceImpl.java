package com.softserveinc.ita.home.blog.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserveinc.ita.home.blog.model.Comment;
import com.softserveinc.ita.home.blog.model.Post;
import com.softserveinc.ita.home.blog.service.PostsService;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-04-15T11:28:34.593463600+03:00[Europe/Helsinki]")
@Controller
public class PostsServiceImpl implements PostsService {

    private static final Logger log = LoggerFactory.getLogger(PostsServiceImpl.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public PostsServiceImpl(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Post> create(@ApiParam(value = "some parameters", required = true) @Valid @RequestBody Post body
    ) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Post>(objectMapper.readValue("{\r\n  \"previewAttachment\" : \"previewAttachment\",\r\n  \"id\" : 0.8008281904610115,\r\n  \"text\" : \"text\",\r\n  \"updatedOn\" : \"2017-07-21T17:32:28Z\",\r\n  \"title\" : \"title\",\r\n  \"createdOn\" : \"2017-07-21T17:32:28Z\",\r\n  \"user\" : \"\",\r\n  \"tags\" : [ {\r\n    \"name\" : \"name\",\r\n    \"id\" : 6.027456183070403\r\n  }, {\r\n    \"name\" : \"name\",\r\n    \"id\" : 6.027456183070403\r\n  } ]\r\n}", Post.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Post>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Post>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> deleteComment(@ApiParam(value = "", required = true) @PathVariable("post_id") long postId
            , @ApiParam(value = "", required = true) @PathVariable("id") long id
    ) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> deletePost(@ApiParam(value = "", required = true) @PathVariable("id") long id
    ) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Comment>> getAllComments(@ApiParam(value = "", required = true) @PathVariable("post_id") long postId
            , @ApiParam(value = "Find by id") @Valid @RequestParam(value = "id", required = false) long id
            , @ApiParam(value = "Find by user name") @Valid @RequestParam(value = "user_name", required = false) String userName
            , @ApiParam(value = "Find by user id") @Valid @RequestParam(value = "user_id", required = false) String userId
            , @ApiParam(value = "In order to execute *asc*, you need to specify in the search *id* parameter. In order to complete the *desc* sorting must be specified in the query parameter *-id* ", allowableValues = "id, -id", defaultValue = "-id") @Valid @RequestParam(value = "sort", required = false, defaultValue = "-id") String sort
            , @ApiParam(value = "") @Valid @RequestParam(value = "page_num", required = false) Integer pageNum
            , @ApiParam(value = "") @Valid @RequestParam(value = "page_size", required = false) Integer pageSize
    ) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Comment>>(objectMapper.readValue("[ {\r\n  \"id\" : 0.8008281904610115,\r\n  \"text\" : \"text\",\r\n  \"postId\" : 6.027456183070403,\r\n  \"updatedOn\" : \"2017-07-21T17:32:28Z\",\r\n  \"user\" : {\r\n    \"firstName\" : \"John\",\r\n    \"lastName\" : \"Smith\",\r\n    \"password\" : \"passworD321\",\r\n    \"role\" : \"guest\",\r\n    \"name\" : \"John78\",\r\n    \"id\" : 0.8008281904610115,\r\n    \"email\" : \"john.smith@example.com\"\r\n  },\r\n  \"createdOn\" : \"2017-07-21T17:32:28Z\"\r\n}, {\r\n  \"id\" : 0.8008281904610115,\r\n  \"text\" : \"text\",\r\n  \"postId\" : 6.027456183070403,\r\n  \"updatedOn\" : \"2017-07-21T17:32:28Z\",\r\n  \"user\" : {\r\n    \"firstName\" : \"John\",\r\n    \"lastName\" : \"Smith\",\r\n    \"password\" : \"passworD321\",\r\n    \"role\" : \"guest\",\r\n    \"name\" : \"John78\",\r\n    \"id\" : 0.8008281904610115,\r\n    \"email\" : \"john.smith@example.com\"\r\n  },\r\n  \"createdOn\" : \"2017-07-21T17:32:28Z\"\r\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Comment>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Comment>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Post>> getAllPosts(@ApiParam(value = "Find by id") @Valid @RequestParam(value = "id", required = false) long id
            , @ApiParam(value = "Find by tag id") @Valid @RequestParam(value = "tag_id", required = false) String tagId
            , @ApiParam(value = "Find by tag name") @Valid @RequestParam(value = "tag_name", required = false) String tagName
            , @ApiParam(value = "Find by user id") @Valid @RequestParam(value = "user_id", required = false) String userId
            , @ApiParam(value = "In order to execute *asc*, you need to specify in the search *id* or *title* parameter. In order to complete the *desc* sorting must be specified in the query parameter *-id* ", allowableValues = "id, -id, title, -title", defaultValue = "-id") @Valid @RequestParam(value = "sort", required = false, defaultValue = "-id") String sort
            , @ApiParam(value = "") @Valid @RequestParam(value = "page_num", required = false) Integer pageNum
            , @ApiParam(value = "") @Valid @RequestParam(value = "page_size", required = false) Integer pageSize
    ) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Post>>(objectMapper.readValue("[ {\r\n  \"previewAttachment\" : \"previewAttachment\",\r\n  \"id\" : 0.8008281904610115,\r\n  \"text\" : \"text\",\r\n  \"updatedOn\" : \"2017-07-21T17:32:28Z\",\r\n  \"title\" : \"title\",\r\n  \"createdOn\" : \"2017-07-21T17:32:28Z\",\r\n  \"user\" : \"\",\r\n  \"tags\" : [ {\r\n    \"name\" : \"name\",\r\n    \"id\" : 6.027456183070403\r\n  }, {\r\n    \"name\" : \"name\",\r\n    \"id\" : 6.027456183070403\r\n  } ]\r\n}, {\r\n  \"previewAttachment\" : \"previewAttachment\",\r\n  \"id\" : 0.8008281904610115,\r\n  \"text\" : \"text\",\r\n  \"updatedOn\" : \"2017-07-21T17:32:28Z\",\r\n  \"title\" : \"title\",\r\n  \"createdOn\" : \"2017-07-21T17:32:28Z\",\r\n  \"user\" : \"\",\r\n  \"tags\" : [ {\r\n    \"name\" : \"name\",\r\n    \"id\" : 6.027456183070403\r\n  }, {\r\n    \"name\" : \"name\",\r\n    \"id\" : 6.027456183070403\r\n  } ]\r\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Post>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Post>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Comment> getCommentById(@ApiParam(value = "", required = true) @PathVariable("post_id") long postId
            , @ApiParam(value = "", required = true) @PathVariable("id") long id
    ) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Comment>(objectMapper.readValue("{\r\n  \"id\" : 0.8008281904610115,\r\n  \"text\" : \"text\",\r\n  \"postId\" : 6.027456183070403,\r\n  \"updatedOn\" : \"2017-07-21T17:32:28Z\",\r\n  \"user\" : {\r\n    \"firstName\" : \"John\",\r\n    \"lastName\" : \"Smith\",\r\n    \"password\" : \"passworD321\",\r\n    \"role\" : \"guest\",\r\n    \"name\" : \"John78\",\r\n    \"id\" : 0.8008281904610115,\r\n    \"email\" : \"john.smith@example.com\"\r\n  },\r\n  \"createdOn\" : \"2017-07-21T17:32:28Z\"\r\n}", Comment.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Comment>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Comment>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Post> getPost(@ApiParam(value = "", required = true) @PathVariable("id") long id
    ) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Post>(objectMapper.readValue("{\r\n  \"previewAttachment\" : \"previewAttachment\",\r\n  \"id\" : 0.8008281904610115,\r\n  \"text\" : \"text\",\r\n  \"updatedOn\" : \"2017-07-21T17:32:28Z\",\r\n  \"title\" : \"title\",\r\n  \"createdOn\" : \"2017-07-21T17:32:28Z\",\r\n  \"user\" : \"\",\r\n  \"tags\" : [ {\r\n    \"name\" : \"name\",\r\n    \"id\" : 6.027456183070403\r\n  }, {\r\n    \"name\" : \"name\",\r\n    \"id\" : 6.027456183070403\r\n  } ]\r\n}", Post.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Post>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Post>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Comment> leaveAComment(@ApiParam(value = "some parameters", required = true) @Valid @RequestBody Comment body
            , @ApiParam(value = "", required = true) @PathVariable("post_id") long postId
    ) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Comment>(objectMapper.readValue("{\r\n  \"id\" : 0.8008281904610115,\r\n  \"text\" : \"text\",\r\n  \"postId\" : 6.027456183070403,\r\n  \"updatedOn\" : \"2017-07-21T17:32:28Z\",\r\n  \"user\" : {\r\n    \"firstName\" : \"John\",\r\n    \"lastName\" : \"Smith\",\r\n    \"password\" : \"passworD321\",\r\n    \"role\" : \"guest\",\r\n    \"name\" : \"John78\",\r\n    \"id\" : 0.8008281904610115,\r\n    \"email\" : \"john.smith@example.com\"\r\n  },\r\n  \"createdOn\" : \"2017-07-21T17:32:28Z\"\r\n}", Comment.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Comment>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Comment>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Comment> updateComment(@ApiParam(value = "some parameters", required = true) @Valid @RequestBody Comment body
            , @ApiParam(value = "", required = true) @PathVariable("post_id") long postId
            , @ApiParam(value = "", required = true) @PathVariable("id") long id
    ) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Comment>(objectMapper.readValue("{\r\n  \"id\" : 0.8008281904610115,\r\n  \"text\" : \"text\",\r\n  \"postId\" : 6.027456183070403,\r\n  \"updatedOn\" : \"2017-07-21T17:32:28Z\",\r\n  \"user\" : {\r\n    \"firstName\" : \"John\",\r\n    \"lastName\" : \"Smith\",\r\n    \"password\" : \"passworD321\",\r\n    \"role\" : \"guest\",\r\n    \"name\" : \"John78\",\r\n    \"id\" : 0.8008281904610115,\r\n    \"email\" : \"john.smith@example.com\"\r\n  },\r\n  \"createdOn\" : \"2017-07-21T17:32:28Z\"\r\n}", Comment.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Comment>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Comment>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Post> updatePost(@ApiParam(value = "some parameters", required = true) @Valid @RequestBody Post body
            , @ApiParam(value = "", required = true) @PathVariable("id") long id
    ) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Post>(objectMapper.readValue("{\r\n  \"previewAttachment\" : \"previewAttachment\",\r\n  \"id\" : 0.8008281904610115,\r\n  \"text\" : \"text\",\r\n  \"updatedOn\" : \"2017-07-21T17:32:28Z\",\r\n  \"title\" : \"title\",\r\n  \"createdOn\" : \"2017-07-21T17:32:28Z\",\r\n  \"user\" : \"\",\r\n  \"tags\" : [ {\r\n    \"name\" : \"name\",\r\n    \"id\" : 6.027456183070403\r\n  }, {\r\n    \"name\" : \"name\",\r\n    \"id\" : 6.027456183070403\r\n  } ]\r\n}", Post.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Post>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Post>(HttpStatus.NOT_IMPLEMENTED);
    }

}
