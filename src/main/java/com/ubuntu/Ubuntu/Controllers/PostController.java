package com.ubuntu.Ubuntu.Controllers;

import com.ubuntu.Ubuntu.Models.Posts;
import com.ubuntu.Ubuntu.Models.User;
import com.ubuntu.Ubuntu.Services.PostService;
import com.ubuntu.Ubuntu.Services.UserServive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService){
        this.postService = postService;
    }


    @PostMapping("/post")
    public ResponseEntity<String> createpost(@RequestBody Posts posts) {

        String response = postService.createPost(posts);
        HttpStatus status = checkHttpStatus(response);

        if(status == HttpStatus.OK) return new ResponseEntity<>(response, status);
        else return new ResponseEntity<>(response, status);
    }


    @GetMapping("/post/{id}")
    public ResponseEntity<Posts> getPostById(@PathVariable int id) {

        Posts posts = postService.getPostById(id);

        if(posts.getId() != 0) return new ResponseEntity<>(posts, HttpStatus.OK);
        else return new ResponseEntity<>(posts, HttpStatus.NO_CONTENT);
    }

    @PostMapping("/post/update/{id}")
    public ResponseEntity<String> updatePost(@RequestBody Posts post, @PathVariable int id) {

        String response = postService.updatePost(post,id);
        HttpStatus status = checkHttpStatus(response);

        if(status == HttpStatus.OK) return new ResponseEntity<>(response, status);
        else return new ResponseEntity<>(response, status);
    }

    @DeleteMapping("/post/delete/{id}")
    public ResponseEntity<String> deletePosTById(@PathVariable int id) {

        String response = postService.deletePostById(id);
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
