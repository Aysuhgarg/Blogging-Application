package com.ayush.blog.services.impl;

import com.ayush.blog.entity.User;
import com.ayush.blog.exceptions.ResourseNotFoundException;
import com.ayush.blog.payloads.UserDto;
import com.ayush.blog.repositories.UserRepo;
import com.ayush.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;
    @Override
    public UserDto createUser(UserDto userdto) {

        User user=this.userDtoTouser(userdto);
         User saved=userRepo.save(user);
         return this.userTouserdto(saved);
    }

    @Override
    public UserDto updateUser(UserDto userdto, Integer userId) {

        User user= userRepo.findById(userId).orElseThrow(()->new ResourseNotFoundException("User","id",userId));
        user.setName(userdto.getName());
        user.setEmail(userdto.getEmail());
        user.setPassword(userdto.getPassword());
        user.setAbout(userdto.getAbout());
        User saved= userRepo.save(user);

        return this.userTouserdto(saved);
    }

    @Override
    public UserDto getUserById(Integer userId) {

        User user=userRepo.findById(userId).orElseThrow(()-> new ResourseNotFoundException("User","id",userId));

        return this.userTouserdto(user);
    }

    @Override
    public List<UserDto> getAllUser() {

        List<User> user= userRepo.findAll();
        List<UserDto> userDtos =new ArrayList<>();
        for(User a:user)
        {
            UserDto userdto= this.userTouserdto(a);
            userDtos.add(userdto);
        }
        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {

        User user=userRepo.findById(userId).orElseThrow(()->new ResourseNotFoundException("User","id",userId));
        userRepo.delete(user);
    }

    public User userDtoTouser(UserDto userdto)
    {
        User user = new User();
        user.setId(userdto.getId());
        user.setName(userdto.getName());
        user.setEmail(userdto.getEmail());
        user.setPassword(userdto.getPassword());
        user.setAbout(userdto.getAbout());
        return user;
    }


    public UserDto userTouserdto(User user)
    {
        UserDto userdto = new UserDto();
        userdto.setId(user.getId());
        userdto.setName(user.getName());
        userdto.setEmail(user.getEmail());
        userdto.setPassword(user.getPassword());
        userdto.setAbout(user.getAbout());
        return userdto;
    }
}
