package org.example.employees.controller;

import lombok.AllArgsConstructor;
import org.example.employees.entities.Employee;
import org.example.employees.service.EmployeeServiceImp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employee")
@AllArgsConstructor
public class EmployeeController {
    private final EmployeeServiceImp service ;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody Employee employee){
        service.create(employee);
        return  ResponseEntity.ok("Employee creatd succesfully") ;
    }

    @GetMapping
    public List<Employee> getAll(){
        return  service.getAll() ;
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable int id){
        return service.getById(id) ;
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> supprimer( @PathVariable  int id){
        service.supprimer(id);
        return ResponseEntity.ok("Supprimé avec succes ") ;
    }

    @PutMapping("{id}")
    public ResponseEntity<String> update(@PathVariable int id , @RequestBody Employee e){
        service.update(id, e);
        return ResponseEntity.ok("Modifié avec succes") ;
    }
}
