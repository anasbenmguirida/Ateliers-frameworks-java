package org.example.javajdbctemplate.Blog.entity;

import javax.persistence.*;

@Entity
public class Comment {

    @Id @GeneratedValue
    private int pk ;
    private String contenue ;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user ;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post ;

}
