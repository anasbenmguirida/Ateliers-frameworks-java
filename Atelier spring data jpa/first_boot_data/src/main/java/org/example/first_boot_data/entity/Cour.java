package org.example.first_boot_data.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Entity
@Data
public class Cour {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id ;
    @Column(unique = true)
    private String code ;
    @Column(unique = true)
    private String nom ;

    @ManyToMany
    @JoinTable( name = "cour_filiere",
            joinColumns = @JoinColumn(name = "id_cour"),
            inverseJoinColumns= @JoinColumn(name = "id_filiere"))
    private List<Filiere> filieres ;




}
