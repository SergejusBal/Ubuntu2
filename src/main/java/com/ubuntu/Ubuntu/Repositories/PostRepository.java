package com.ubuntu.Ubuntu.Repositories;

import com.ubuntu.Ubuntu.Models.Posts;
import com.ubuntu.Ubuntu.Models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class PostRepository {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;


    public String createPost(Posts posts){

        if(posts.getUser_id() == 0 || posts.getContent() == null || posts.getTitle() == null) return "Invalid data";

        String sql = "INSERT INTO posts (user_id, title, content)\n" +
                "VALUES (?,?,?);";
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,posts.getUser_id());
            preparedStatement.setString(2,posts.getTitle());
            preparedStatement.setString(3,posts.getContent());

            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

        }catch (SQLException e) {

            System.out.println(e.getMessage());

            return "Database connection failed";
        }

        return "Post was successfully added ";

    }

    public Posts getPostById(int id){


        Posts posts = new Posts();
        String sql = "SELECT * FROM posts WHERE id = ?";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            ResultSet resultSet =  preparedStatement.executeQuery();

            if(!resultSet.next()) return new Posts();

            posts.setId(resultSet.getInt("id"));
            posts.setUser_id(resultSet.getInt("user_id"));
            posts.setTitle(resultSet.getString("title"));
            posts.setContent(resultSet.getString("content"));

        }catch (SQLException e) {

            System.out.println(e.getMessage());
            return new Posts();
        }

        return posts;
    }


    public String updatePost(Posts posts, int id){

        if(posts.getUser_id() == 0 || posts.getContent() == null || posts.getTitle() == null) return "Invalid data";

        String sql = "UPDATE posts SET user_id = ?, title = ?, content = ? WHERE id = ?";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,posts.getUser_id());
            preparedStatement.setString(2,posts.getTitle());
            preparedStatement.setString(3, posts.getContent());
            preparedStatement.setLong(4,id);

            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

        }catch (SQLException e) {

            System.out.println(e.getMessage());

            return "Database connection failed";
        }

        return "Post was successfully added ";

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
