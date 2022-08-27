package com.ayush.blog.services.impl;

import com.ayush.blog.entity.Category;
import com.ayush.blog.entity.Post;
import com.ayush.blog.entity.User;
import com.ayush.blog.exceptions.ResourseNotFoundException;
import com.ayush.blog.payloads.PostDto;
import com.ayush.blog.repositories.CategoryRepo;
import com.ayush.blog.repositories.PostRepo;
import com.ayush.blog.repositories.UserRepo;
import com.ayush.blog.services.PostServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostServices {

    @Autowired
    private PostRepo postRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CategoryRepo categoryRepo;



    @Override
    public List<PostDto> getAllPost() {
        List<Post> allpost =postRepo.findAll();
        List<PostDto> allpostdto =new ArrayList<>();
        for(Post each: allpost)
        {
            PostDto postdto= this.postToDto(each);
            allpostdto.add(postdto);
        }
        return allpostdto;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post =postRepo.findById(postId).orElseThrow(()->new ResourseNotFoundException("Post","postId",postId));
        PostDto postDto =modelMapper.map(post,PostDto.class);
        return postDto;
    }

    @Override
    public PostDto createPost(PostDto postdto,Integer userId,Integer categoryId) {
        User user=userRepo.findById(userId).orElseThrow(()->new ResourseNotFoundException("User","userId",userId));
        Category category =categoryRepo.findById(categoryId).orElseThrow(()->new ResourseNotFoundException("Category","categoryId",categoryId));
        Post post= modelMapper.map(postdto,Post.class);
        post.setImage("default.jpg");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        Post newpost =this.postRepo.save(post);

        return this.modelMapper.map(newpost,PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postdto,Integer postId) {
        Post post= postRepo.findById(postId).orElseThrow(()->new ResourseNotFoundException("Post","postId",postId));
        post.setContent(postdto.getContent());
        post.setImage(postdto.getImage());
        post.setTitle(postdto.getTitle());

        Post update=postRepo.save(post);



        return modelMapper.map(update,PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        postRepo.deleteById(postId);
    }

    @Override
    public List<PostDto> getPostByCategory(Integer categoryId) {
        Category cat= categoryRepo.findById(categoryId).orElseThrow(()->new ResourseNotFoundException("Category","categoryId",categoryId));
        List<Post> post =postRepo.findByCategory(cat);
        List<PostDto> postdtos=post.stream().map((eachPost)-> this.modelMapper.map(eachPost,PostDto.class)).collect(Collectors.toList());
        return postdtos;
    }


    @Override
    public List<PostDto> getPostByUser(Integer userId) {
        User user= userRepo.findById(userId).orElseThrow(()->new ResourseNotFoundException("User","UserId",userId));
        List<Post> posts= postRepo.findByUser(user);

        List<PostDto> postdtos =posts.stream().map((eachpost)->modelMapper.map(eachpost,PostDto.class)).collect(Collectors.toList());
        return postdtos;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        return null;
    }



    public PostDto postToDto(Post post)
    {
        PostDto dto =modelMapper.map(post,PostDto.class);
        return dto;
    }
    public Post Topost(PostDto dto)
    {
        Post post= modelMapper.map(dto,Post.class);
        return post;
    }
}
