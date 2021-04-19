/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (5.0.1).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.itacademy.blog.api;

import java.math.BigDecimal;
import com.itacademy.blog.model.Error;
import com.itacademy.blog.model.User;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2021-04-15T19:31:49.707944300+03:00[Europe/Kiev]")
@Validated
@Api(value = "users", description = "the users API")
public interface UsersApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * DELETE /users/{id} : Delete user by ID
     * Delete a user
     *
     * @param id  (required)
     * @return OK (status code 204)
     *         or The payload contains an error (status code 400)
     *         or The specified resource was not found (status code 404)
     *         or The unknown error appeard. Check your payload or contact support. (status code 200)
     */
    @ApiOperation(value = "Delete user by ID", nickname = "deleteUser", notes = "Delete a user", response = User.class, authorizations = {
        
        @Authorization(value = "basicAuth")
         }, tags={ "users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 204, message = "OK", response = User.class),
        @ApiResponse(code = 400, message = "The payload contains an error", response = Error.class),
        @ApiResponse(code = 404, message = "The specified resource was not found", response = Error.class),
        @ApiResponse(code = 200, message = "The unknown error appeard. Check your payload or contact support.", response = Error.class) })
    @DeleteMapping(
        value = "/users/{id}",
        produces = { "application/json" }
    )
    default ResponseEntity<Void> deleteUser(@ApiParam(value = "",required=true) @PathVariable("id") BigDecimal id) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"firstName\" : \"John\", \"lastName\" : \"Smith\", \"password\" : \"passworD321\", \"role\" : \"user\", \"name\" : \"John78\", \"id\" : 0.8008281904610115, \"email\" : \"john.smith@example.com\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /users : This gets all users
     * Return all users with paggination, could be sorted
     *
     * @param id Find by id (optional)
     * @param name Find by name (optional)
     * @param sort In order to execute *asc*, you need to specify in the search *id* or *name* parameter. In order to complete the *desc* sorting must be specified in the query parameter *-id*  (optional, default to -id)
     * @param pageNum  (optional)
     * @param pageSize  (optional)
     * @return OK (status code 200)
     *         or The payload contains an error (status code 400)
     *         or The unknown error appeard. Check your payload or contact support. (status code 200)
     */
    @ApiOperation(value = "This gets all users", nickname = "getAllUsers", notes = "Return all users with paggination, could be sorted", response = User.class, responseContainer = "List", authorizations = {
        
        @Authorization(value = "basicAuth")
         }, tags={ "users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = User.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "The payload contains an error", response = Error.class),
        @ApiResponse(code = 200, message = "The unknown error appeard. Check your payload or contact support.", response = Error.class) })
    @GetMapping(
        value = "/users",
        produces = { "application/json" }
    )
    default ResponseEntity<List<User>> getAllUsers(@ApiParam(value = "Find by id") @Valid @RequestParam(value = "id", required = false) BigDecimal id,@ApiParam(value = "Find by name") @Valid @RequestParam(value = "name", required = false) String name,@ApiParam(value = "In order to execute *asc*, you need to specify in the search *id* or *name* parameter. In order to complete the *desc* sorting must be specified in the query parameter *-id* ", allowableValues = "id, -id, name, -name", defaultValue = "-id") @Valid @RequestParam(value = "sort", required = false, defaultValue="-id") String sort,@ApiParam(value = "") @Valid @RequestParam(value = "page_num", required = false) Integer pageNum,@ApiParam(value = "") @Valid @RequestParam(value = "page_size", required = false) Integer pageSize) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"firstName\" : \"John\", \"lastName\" : \"Smith\", \"password\" : \"passworD321\", \"role\" : \"user\", \"name\" : \"John78\", \"id\" : 0.8008281904610115, \"email\" : \"john.smith@example.com\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /users/{id} : Get user by ID
     * Returns a user
     *
     * @param id  (required)
     * @return OK (status code 200)
     *         or The payload contains an error (status code 400)
     *         or The specified resource was not found (status code 404)
     *         or The unknown error appeard. Check your payload or contact support. (status code 200)
     */
    @ApiOperation(value = "Get user by ID", nickname = "getUser", notes = "Returns a user", response = User.class, authorizations = {
        
        @Authorization(value = "basicAuth")
         }, tags={ "users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = User.class),
        @ApiResponse(code = 400, message = "The payload contains an error", response = Error.class),
        @ApiResponse(code = 404, message = "The specified resource was not found", response = Error.class),
        @ApiResponse(code = 200, message = "The unknown error appeard. Check your payload or contact support.", response = Error.class) })
    @GetMapping(
        value = "/users/{id}",
        produces = { "application/json" }
    )
    default ResponseEntity<User> getUser(@ApiParam(value = "",required=true) @PathVariable("id") BigDecimal id) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"firstName\" : \"John\", \"lastName\" : \"Smith\", \"password\" : \"passworD321\", \"role\" : \"user\", \"name\" : \"John78\", \"id\" : 0.8008281904610115, \"email\" : \"john.smith@example.com\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * POST /users : Sign up a new user
     *
     * @param user some parameters (required)
     * @return OK (status code 201)
     *         or The payload contains an error (status code 400)
     *         or The unknown error appeard. Check your payload or contact support. (status code 200)
     */
    @ApiOperation(value = "Sign up a new user", nickname = "signUp", notes = "", response = User.class, authorizations = {
        
        @Authorization(value = "basicAuth")
         }, tags={ "users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 201, message = "OK", response = User.class),
        @ApiResponse(code = 400, message = "The payload contains an error", response = Error.class),
        @ApiResponse(code = 200, message = "The unknown error appeard. Check your payload or contact support.", response = Error.class) })
    @PostMapping(
        value = "/users",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<User> signUp(@ApiParam(value = "some parameters" ,required=true )  @Valid @RequestBody User user) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"firstName\" : \"John\", \"lastName\" : \"Smith\", \"password\" : \"passworD321\", \"role\" : \"user\", \"name\" : \"John78\", \"id\" : 0.8008281904610115, \"email\" : \"john.smith@example.com\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * PUT /users/{id} : Update user by ID
     * Updates a user
     *
     * @param id  (required)
     * @param user some parameters (required)
     * @return OK (status code 200)
     *         or The payload contains an error (status code 400)
     *         or The specified resource was not found (status code 404)
     *         or The unknown error appeard. Check your payload or contact support. (status code 200)
     */
    @ApiOperation(value = "Update user by ID", nickname = "updateUser", notes = "Updates a user", response = User.class, authorizations = {
        
        @Authorization(value = "basicAuth")
         }, tags={ "users", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = User.class),
        @ApiResponse(code = 400, message = "The payload contains an error", response = Error.class),
        @ApiResponse(code = 404, message = "The specified resource was not found", response = Error.class),
        @ApiResponse(code = 200, message = "The unknown error appeard. Check your payload or contact support.", response = Error.class) })
    @PutMapping(
        value = "/users/{id}",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    default ResponseEntity<User> updateUser(@ApiParam(value = "",required=true) @PathVariable("id") BigDecimal id,@ApiParam(value = "some parameters" ,required=true )  @Valid @RequestBody User user) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"firstName\" : \"John\", \"lastName\" : \"Smith\", \"password\" : \"passworD321\", \"role\" : \"user\", \"name\" : \"John78\", \"id\" : 0.8008281904610115, \"email\" : \"john.smith@example.com\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
