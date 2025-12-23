package org.example.employees.controller;


import lombok.AllArgsConstructor;
import org.example.employees.entities.Departement;
import org.example.employees.service.DepartementImp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departement")
@AllArgsConstructor
public class DepartementController {

    private final DepartementImp service ;

    @GetMapping()
    public List<Departement> getAll(){
        return service.getAll() ;
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody Departement departement){
        service.create(departement);
        return ResponseEntity.ok("Departement creer avec succes ") ;
    }

    @PutMapping("{id}")
    public ResponseEntity<String> update(@PathVariable int id , @RequestBody Departement departement){
        service.update(id , departement);
        return ResponseEntity.ok("Modifie avec succes") ;
    }

    @DeleteMapping
    public ResponseEntity<String> supprimer(int id){
        service.supprimer(id) ;
        return ResponseEntity.ok("Supprimé avec succes " );

    }
}
