package com.softserveinc.ita.home.home_project_blog.controllers;

//import com.example.demo.Service.UserServiceMap;

import com.softserveinc.ita.home.home_project_blog.service.IUserService;
import com.softserveinc.ita.home.home_project_blog.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/api/0/users")//, consumes = "application/json", produces = "application/json")
public class UsersController {
    @Autowired
    private IUserService userService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        Optional<User> user = userService.getById(id);
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<User> signUp(@RequestBody User user){
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<User> updateUser(@PathVariable Long id,
                                           @RequestBody User user){
        if (id!=user.getId()){
            user.setId(id);
            //message id user doesn't equal id in user
            //return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        Optional<User> newUser = userService.update(user);
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
