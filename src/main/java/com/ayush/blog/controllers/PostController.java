package com.ayush.blog.controllers;

import com.ayush.blog.entity.Category;
import com.ayush.blog.entity.Post;
import com.ayush.blog.payloads.ApiResponse;
import com.ayush.blog.payloads.CategoryDto;
import com.ayush.blog.payloads.PostDto;
import com.ayush.blog.services.PostServices;
import com.ayush.blog.services.impl.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostServiceImpl postServices;

    //Get all post
    @GetMapping("/posts")
    public List<PostDto> getAllPost()
    {
        return postServices.getAllPost();
    }

    //Get post by post id
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId)
    {
        return new ResponseEntity<PostDto>(postServices.getPostById(postId), HttpStatus.OK);
    }

    //Get post by category
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getByCategory(@PathVariable Integer categoryId)
    {
        return new ResponseEntity<List<PostDto>>(postServices.getPostByCategory(categoryId),HttpStatus.OK);
    }

    //Get post by user
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId)
    {
        return new ResponseEntity<List<PostDto>>(postServices.getPostByUser(userId),HttpStatus.OK);
    }

    //Create new post
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createNewPost(@RequestBody PostDto newPost,@PathVariable Integer userId,@PathVariable Integer categoryId)
    {
        return new ResponseEntity<PostDto>(postServices.createPost(newPost,userId,categoryId),HttpStatus.CREATED);
    }

    //update post by id
    @PutMapping("posts/{postId}")
    public ResponseEntity<PostDto> UpdatePost(@PathVariable Integer postId, @RequestBody PostDto updatePost)
    {
        return new ResponseEntity<>(postServices.updatePost(updatePost,postId),HttpStatus.OK);
    }

    //delete post
    @DeleteMapping("/posts/{postId}")
    public ApiResponse deletePost(@PathVariable Integer postId)
    {
        postServices.deletePost(postId);
        return new ApiResponse("Post is successfully deleted !!","tru e");
    }
}
