package com.ayush.blog.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {


    private Integer categoryId;
    @NotEmpty
    @Size(min=10,message = "Description must be minimum of 10 character!!")
    private String categoryDescription;
    @NotEmpty
    @Size(min=3,message = "Title must be minimum of 3 character!!")
    private String categoryTitle;

}
