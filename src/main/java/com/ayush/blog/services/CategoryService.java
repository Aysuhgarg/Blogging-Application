package com.ayush.blog.services;

import com.ayush.blog.entity.Category;
import com.ayush.blog.entity.Post;
import com.ayush.blog.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(CategoryDto categorydata,Integer categoryId);
    void deleteCategory(Integer categoryId);
    List<CategoryDto> getAllCategory();
    CategoryDto getCategoryById(Integer categoryId);
}
