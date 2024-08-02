package com.ubuntu.Ubuntu.Controllers;

import com.ubuntu.Ubuntu.Models.User;
import com.ubuntu.Ubuntu.Services.UserServive;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {


    private UserServive userServive;

    @Autowired
    public UserController(UserServive userServive){
        this.userServive = userServive;
    }

    @GetMapping("/test/{test}")
    public ResponseEntity<String> Test(@PathVariable String test) {

        return new ResponseEntity<>(test,HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<String> createUser(@RequestBody User user) {

        String response = userServive.createUser(user);
        HttpStatus status = checkHttpStatus(response);

        if(status == HttpStatus.OK) return new ResponseEntity<>(response, status);
        else return new ResponseEntity<>(response, status);
    }


    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {

        User user = userServive.getUserById(id);

        if(user.getId() != 0) return new ResponseEntity<>(user, HttpStatus.OK);
        else return new ResponseEntity<>(user, HttpStatus.NO_CONTENT);
    }

    @PostMapping("/user/update/{id}")
    public ResponseEntity<String> updateUser(@RequestBody User user, @PathVariable int id) {

        String response = userServive.updateUser(user,id);
        HttpStatus status = checkHttpStatus(response);

        if(status == HttpStatus.OK) return new ResponseEntity<>(response, status);
        else return new ResponseEntity<>(response, status);
    }

    @DeleteMapping("/user/delete/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable int id) {

        String response = userServive.deleteUserById(id);
        HttpStatus status = checkHttpStatus(response);

        if(status == HttpStatus.OK) return new ResponseEntity<>(response, status);
        else return new ResponseEntity<>(response, status);
    }

    private HttpStatus checkHttpStatus(String response){

        switch (response){
            case "Database connection failed":
                return HttpStatus.INTERNAL_SERVER_ERROR;
            case "User already exists":
                return HttpStatus.CONFLICT;
            case "Invalid data":
                return HttpStatus.BAD_REQUEST;
            default:
                return HttpStatus.OK;
        }

    }




}
