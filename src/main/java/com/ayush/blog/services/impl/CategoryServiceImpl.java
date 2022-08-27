package com.ayush.blog.services.impl;

import com.ayush.blog.entity.Category;
import com.ayush.blog.entity.Post;
import com.ayush.blog.exceptions.ResourseNotFoundException;
import com.ayush.blog.payloads.CategoryDto;
import com.ayush.blog.repositories.CategoryRepo;
import com.ayush.blog.services.CategoryService;
import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo cr;
    @Autowired
    private ModelMapper model;


    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {

        Category category=CategoryDtoToCategory(categoryDto);
        Category cat=cr.save(category);
        return CategoryToDTO(cat) ;
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categorydata, Integer categoryId) {
        Category cat= cr.findById(categoryId).orElseThrow(()->new ResourseNotFoundException("Category","CategoryId",categoryId));
        cat.setCategoryTitle(categorydata.getCategoryTitle());
        cat.setCategoryDescription(categorydata.getCategoryDescription());
        cr.save(cat);
        CategoryDto catdto= this.CategoryToDTO(cat);
        return catdto;
    }

    @Override
    public void deleteCategory(Integer categoryId) {

         cr.deleteById(categoryId);
    }

    @Override
    public List<CategoryDto> getAllCategory() {

        List<Category> categorys= cr.findAll();
        List<CategoryDto> catdtolist= new ArrayList<>();

        for(Category cat:categorys)
        {
            CategoryDto catdto= this.CategoryToDTO(cat);
            catdtolist.add(catdto);
        }
        return catdtolist;
    }

    @Override
    public CategoryDto getCategoryById(Integer categoryId) {
        Category cat=cr.findById(categoryId).orElseThrow(()->new ResourseNotFoundException("Category","CategoryId",categoryId));
        CategoryDto catdto = this.CategoryToDTO(cat);
        return catdto;
    }


    public CategoryDto CategoryToDTO(Category cat)
    {
        CategoryDto catdto= model.map(cat,CategoryDto.class);
        return catdto;
    }

    public Category CategoryDtoToCategory(CategoryDto catdto)
    {
        Category cat= model.map(catdto,Category.class);
        return cat;
    }
}
