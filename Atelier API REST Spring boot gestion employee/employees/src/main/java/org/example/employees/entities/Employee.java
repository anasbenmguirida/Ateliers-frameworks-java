package org.example.employees.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy =  GenerationType.SEQUENCE)
    private int id ;

    private String nomComplet ;
    private double salaire ;

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private Date dateEntree ;

    @ManyToOne
    @JoinColumn(name = "dep_id")
    private Departement departement ;
}
