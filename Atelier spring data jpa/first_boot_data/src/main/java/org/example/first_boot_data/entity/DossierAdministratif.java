package org.example.first_boot_data.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
@Data
public class DossierAdministratif {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id ;
    private Date dateCreation  ;

    @OneToOne
    @JoinColumn(name = "id_eleve")
    private Eleve eleve ;
}
