package com.ayush.blog.payloads;

import com.ayush.blog.entity.Category;
import com.ayush.blog.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
public class PostDto {


    private Integer postId;
    private String title;
    private  String content;
    private String image;
    private Date addedDate;

    private CategoryDto category;
    private UserDto user;

}
