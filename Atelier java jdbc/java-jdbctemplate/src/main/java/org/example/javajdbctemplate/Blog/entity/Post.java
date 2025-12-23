package org.example.javajdbctemplate.Blog.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    private String contenue  ;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user  ;

    @OneToMany(fetch = FetchType.LAZY  , cascade = CascadeType.ALL)
    private List<Comment> comments ;
}
