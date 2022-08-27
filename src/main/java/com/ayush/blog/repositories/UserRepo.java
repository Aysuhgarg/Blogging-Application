package com.ayush.blog.repositories;

import com.ayush.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {
}
