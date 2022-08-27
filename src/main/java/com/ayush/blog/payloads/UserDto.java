package com.ayush.blog.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.*;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private int id;
    @NotEmpty
    @Size(min=3,message = "Username must be minimum of 3 character!!")
    private String name;
    @Email(message = "Email address is not valid")
    private String email;
    @NotEmpty()
    @Size(min = 5,max = 11,message = "Password must be of minimum 5 character and maximum 11 character!!")
    private String password;
    @NotEmpty
    private String about;
}
