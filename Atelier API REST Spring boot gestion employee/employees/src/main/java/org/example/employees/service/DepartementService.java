package org.example.employees.service;

import org.example.employees.entities.Departement;

import java.util.List;

public interface DepartementService {
    void create(Departement d) ;
    List<Departement> getAll() ;
    void update(int id , Departement departement) ;
    void supprimer(int id) ;
    Departement getById(int id) ;


}
