package org.example.first_boot_data.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Filiere {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id ;
    private String nomFiliere ;
    private String description ;

    @OneToMany(fetch =  FetchType.LAZY , mappedBy = "filiere" ,cascade = CascadeType.ALL)
    private List<Eleve> etudiants ;


    @ManyToMany(mappedBy = "filieres")
    private List<Cour> cours ;
}
