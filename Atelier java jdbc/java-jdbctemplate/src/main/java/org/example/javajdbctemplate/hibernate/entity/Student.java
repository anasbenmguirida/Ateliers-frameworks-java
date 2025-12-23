package org.example.javajdbctemplate.hibernate.entity;

import org.springframework.data.web.JsonPath;

import javax.persistence.*;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id  ;
    private String name ;

    @OneToOne
    @JoinColumn(name = "dossier_id")
    private DossierStudent dossier ;
}
