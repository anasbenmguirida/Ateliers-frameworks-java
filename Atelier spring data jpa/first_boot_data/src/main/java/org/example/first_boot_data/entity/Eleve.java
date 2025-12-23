package org.example.first_boot_data.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Eleve {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String matricule;
    private String nomComplet;

    @ManyToOne
    @JoinColumn(name = "id_filiere")
    private Filiere filiere ;

    @OneToOne(mappedBy = "eleve")
    private DossierAdministratif dossierAdministratif ;

}

