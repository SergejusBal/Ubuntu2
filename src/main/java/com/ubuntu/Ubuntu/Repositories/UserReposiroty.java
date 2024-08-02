package com.ubuntu.Ubuntu.Repositories;

import com.ubuntu.Ubuntu.Models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class UserReposiroty {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    public String createUser(User user){

        if(user.getName() == null || user.getEmail() == null ) return "Invalid data";

        String sql = "INSERT INTO users (name,email)\n" +
                "VALUES (?,?);";
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getEmail());

            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

        }catch (SQLException e) {

            System.out.println(e.getMessage());

            if (e.getErrorCode() == 1062) return "User already exists";

            return "Database connection failed";
        }

        return "User was successfully added ";

    }

    public User getUserById(int id){


        User user = new User();
        String sql = "SELECT * FROM users WHERE id = ?";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            ResultSet resultSet =  preparedStatement.executeQuery();

            if(!resultSet.next()) return new User();

            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
            user.setEmail(resultSet.getString("email"));

        }catch (SQLException e) {

            System.out.println(e.getMessage());
            return new User();
        }

        return user;
    }


    public String updateUser(User user, int id){

        if(user.getName() == null || user.getEmail() == null) return "Invalid data";

        String sql = "UPDATE users SET name = ?, email = ? WHERE id = ?";
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getEmail());
            preparedStatement.setLong(3,id);

            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

        }catch (SQLException e) {

            System.out.println(e.getMessage());

            if (e.getErrorCode() == 1062) return "User already exists";

            return "Database connection failed";
        }

        return "User was successfully added ";

    }

    public String deleteUserById(int id){
        deletePostById(id);

        String sql = "DELETE FROM users WHERE id = ?;";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();

        }catch (SQLException e) {

            System.out.println(e.getMessage());
            return "Database connection failed";
        }

        return "User was successfully deleted";
    }

    public String deletePostById(int id){

        String sql = "DELETE FROM posts WHERE id = ?;";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();

        }catch (SQLException e) {

            System.out.println(e.getMessage());
            return "Database connection failed";
        }

        return "Post was successfully deleted";
    }












}
