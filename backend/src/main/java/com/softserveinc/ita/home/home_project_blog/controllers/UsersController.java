package com.softserveinc.ita.home.home_project_blog.controllers;


import com.softserveinc.ita.home.home_project_blog.dto.CreateUserDto;
import com.softserveinc.ita.home.home_project_blog.dto.PageUserDto;
import com.softserveinc.ita.home.home_project_blog.dto.UserDto;
import com.softserveinc.ita.home.home_project_blog.service.IUserService;
import com.softserveinc.ita.home.home_project_blog.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@Controller
//@RequiredArgsConstructor
@RequestMapping(path = "/api/0/users", produces = "application/json")
public class UsersController {
    private final IUserService userService;

    public UsersController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<User>> getAllUsers(
            @RequestParam(defaultValue = "0") Integer page_num,
            @RequestParam(defaultValue = "50") Integer page_size,
            @RequestParam(defaultValue = "-id") String sort
    ){;
        Page<User> pagedResult = userService.findAll(page_num, page_size,sort);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("X-Total-Count",
                String.valueOf(pagedResult.getTotalPages()));
        List<User> users = pagedResult.getContent();
        return new ResponseEntity<>(users, responseHeaders, HttpStatus.OK);
    }

//    @GetMapping(produces = "application/json")
//    public ResponseEntity<List<UserDto>> getAllUsers(
//            @RequestParam(required = false, value = "id") @Valid Long id,
//            @RequestParam(required = false, value = "name") @Valid String name,
//            @RequestParam(required = false, value = "sort") @Valid String sort,
//            @RequestParam(required = false, value = "pageNum") @Valid Integer pageNum,
//            @RequestParam(required = false, value = "pageSize") @Valid Integer pageSize
//            @RequestParam(Optional<Long> id),
//            @RequestParam(Optional<String> name),
//            @RequestParam(defaultValue = "0") Integer page_num,
//            @RequestParam(defaultValue = "50") Integer page_size,
//            @RequestParam(defaultValue = "-id") String sort
//    ){
////        return "ID: " + id.orElseGet(() -> "not provided");
//        PageUserDto pagedResult = userService.findAll(page_num, page_size,sort);
////        List<UserDto> userDtos;
////        if (pagedResult.hasContent()) {
////            userDtos = mapper.toUserDtos(pagedResult.getContent());
////        } else {
////            userDtos = new ArrayList<UserDto>();
////        }
//        HttpHeaders responseHeaders = new HttpHeaders();
//        responseHeaders.set("X-Total-Count",
//                String.valueOf(pagedResult.getTotalPages()));
//        return new ResponseEntity<>(pagedResult.getContent(), responseHeaders, HttpStatus.OK);
//    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long id) {
        Optional<UserDto> user = userService.getById(id);
        return user.map(value -> new ResponseEntity<>(value,
                HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<User> signUp(@RequestBody CreateUserDto user){
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id,
                                           @RequestBody CreateUserDto user){
       /* if (id!=user.getId()){
            user.setId(id);
            //message id user doesn't equal id in user
            //return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }*/
        Optional<UserDto> newUser = userService.update(id,user);
        return newUser.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//new ResponseError("404","User with id=\""+id+"\" hasn't been found."
//        if (Objects.isNull(userService.update(longId))) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<User> deleteUser(@PathVariable Long id){
        if (userService.delete(id)) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


           // @RequestParam(required = false, value = "id") Long id,
            //@RequestParam(required = false, value = "name") String name
//    ) {
        /*
        if (!StringUtils.isEmpty(id)) {
            ResponseEntity<User> resp = getUserById(id);
            if (resp.hasBody()) {
                List<User> users = new ArrayList<User>();
                users.add(resp.getBody());
                return new ResponseEntity<List<User>>(users, HttpStatus.OK);
            }
            return new ResponseEntity<List<User>>(resp.getStatusCode());
        }
        if (!StringUtils.isEmpty(name)) {
            List<User> usersByName = userService.getByName(name);
//            if (optUser.isPresent()) {
////            for (User user : users) {
////                if (user.getName().equalsIgnoreCase(name)) {
//                    List<User> users = new ArrayList<User>();
//                    users.add(optUser.get());
                    return new ResponseEntity<List<User>>( usersByName, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }*/
     //   var users = (List<User>) userService.findAll();
//        return new ResponseEntity<List<User>>(userService.findAll(), HttpStatus.OK);
//    }

}
