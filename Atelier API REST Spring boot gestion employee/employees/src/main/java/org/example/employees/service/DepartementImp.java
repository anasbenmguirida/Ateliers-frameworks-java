package org.example.employees.service;

import lombok.AllArgsConstructor;
import org.example.employees.entities.Departement;
import org.example.employees.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DepartementImp implements DepartementService {

    private final DepartmentRepository repository ;

    @Override
    public void create(Departement d) {
        repository.save(d) ;
        System.out.println("departement creé avec succe");
    }

    @Override
    public List<Departement> getAll() {
        return this.repository.findAll() ;
    }

    @Override
    public void update(int id, Departement departement) {
        Departement d = getById(id) ;
        if(d!=null){
            d.setNomDepartement(departement.getNomDepartement());
        }
        return;
    }

    @Override
    public void supprimer(int id) {
        Departement departement =getById(id) ;
        if(departement!=null) {
            repository.delete(departement) ;
        }
        System.out.println("le departement nexiste pas *******");
        return;
    }

    @Override
    public Departement getById(int id) {
        Departement departement = repository.findById(id).orElse(null) ;
        return departement;
    }
}
