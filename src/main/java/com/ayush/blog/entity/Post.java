package com.ayush.blog.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.security.PrivateKey;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;

    private String title;
    private String content;
    private String image;
    private Date addedDate;

    @ManyToOne
    @JoinColumn(name="categoryId")
    private Category category;
    @ManyToOne
    private User user;

}
