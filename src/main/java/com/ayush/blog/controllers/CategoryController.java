package com.ayush.blog.controllers;

import com.ayush.blog.entity.Category;
import com.ayush.blog.payloads.CategoryDto;
import com.ayush.blog.services.CategoryService;
import com.ayush.blog.services.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryServiceImpl cs;

    @GetMapping("/")
    public List<CategoryDto> getallcategories()
    {
        List<CategoryDto> allCategory=cs.getAllCategory();
        return allCategory;
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getcategorybyid(@PathVariable Integer categoryId)
    {
        return new ResponseEntity<CategoryDto>(cs.getCategoryById(categoryId),HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto category)
    {
        return new ResponseEntity<CategoryDto>(cs.createCategory(category), HttpStatus.CREATED);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updatecategory(@PathVariable Integer categoryId,@RequestBody CategoryDto categoryDto)
    {
        return new ResponseEntity<CategoryDto>(cs.updateCategory(categoryDto,categoryId),HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    public void deletcategory(@PathVariable Integer categoryId)
    {
        cs.deleteCategory(categoryId);
    }

}
