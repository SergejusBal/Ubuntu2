package com.ubuntu.Ubuntu.Services;

import com.ubuntu.Ubuntu.Models.Posts;
import com.ubuntu.Ubuntu.Models.User;
import com.ubuntu.Ubuntu.Repositories.PostRepository;
import com.ubuntu.Ubuntu.Repositories.UserReposiroty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private PostRepository postRepository;
    @Autowired
    public PostService(PostRepository postRepository){
        this.postRepository = postRepository;
    }


    public String createPost(Posts posts){
        return postRepository.createPost(posts);
    }

    public Posts getPostById(int id){
        return postRepository.getPostById(id);
    }

    public String updatePost(Posts posts, Long id){
        return postRepository.updatePost(posts, id);
    }

    public String deletePostById(int id){
        return postRepository.deletePostById(id);
    }
}
