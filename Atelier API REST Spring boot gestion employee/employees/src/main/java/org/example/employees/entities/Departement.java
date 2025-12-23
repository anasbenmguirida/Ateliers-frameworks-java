package org.example.employees.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Departement {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id ;

    private String nomDepartement ;

    @OneToMany(mappedBy = "departement" , fetch = FetchType.LAZY)
    private List<Employee> employees ;

}
