package com.ayush.blog.controllers;

import com.ayush.blog.entity.User;
import com.ayush.blog.payloads.UserDto;
import com.ayush.blog.services.impl.UserServiceImpl;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userdto)
    {
       UserDto createdNewUser=userService.createUser(userdto);

        return new ResponseEntity<>(createdNewUser, HttpStatus.CREATED);
    }
    @GetMapping("/")
    public List<UserDto> getallUser()
    {
        return userService.getAllUser();
    }

    @GetMapping("/{userId}")
    public UserDto getuserById(@PathVariable  Integer userId)
    {
        return userService.getUserById(userId);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer userId)
    {
        userService.deleteUser(userId);
        return new ResponseEntity(Map.of("message","User deleted successfully"),HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userdto ,@PathVariable Integer userId)
    {
        UserDto updatedUser=userService.updateUser(userdto,userId);
        return ResponseEntity.ok(updatedUser);
    }
}
