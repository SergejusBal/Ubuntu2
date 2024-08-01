package com.ubuntu.Ubuntu.Services;

import com.ubuntu.Ubuntu.Models.User;
import com.ubuntu.Ubuntu.Repositories.UserReposiroty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServive {

    private UserReposiroty userReposiroty;
    @Autowired
    public UserServive(UserReposiroty userReposiroty){
        this.userReposiroty = userReposiroty;

    }


    public String createUser(User user){
        return userReposiroty.createUser(user);
    }

    public User getUserById(int id){
        return userReposiroty.getUserById(id);
    }

    public String updateUser(User user, Long id){
        return userReposiroty.updateUser(user, id);
    }

    public String deleteUserById(int id){
        return userReposiroty.deleteUserById(id);
    }


}
