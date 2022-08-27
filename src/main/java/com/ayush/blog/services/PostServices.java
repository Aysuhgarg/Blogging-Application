package com.ayush.blog.services;

import com.ayush.blog.entity.Post;
import com.ayush.blog.payloads.PostDto;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface PostServices {

    public List<PostDto> getAllPost();

    public PostDto getPostById(Integer postId);

    public PostDto createPost(PostDto postdto,Integer userId,Integer categoryId);

    public PostDto updatePost(PostDto postdto,Integer postId);

    public void deletePost(Integer postId);

    List<PostDto> getPostByCategory(Integer categoryId);

    List<PostDto> getPostByUser(Integer userId);

    List<PostDto> searchPosts(String keyword);

}
