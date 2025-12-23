package org.example.javajdbctemplate.Blog.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Post> posts;

}
