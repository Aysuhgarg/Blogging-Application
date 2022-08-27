package com.ayush.blog.repositories;

import com.ayush.blog.entity.Category;
import com.ayush.blog.entity.Post;
import com.ayush.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {

    List<Post> findByCategory(Category category);
    List<Post> findByUser(User user);
}
