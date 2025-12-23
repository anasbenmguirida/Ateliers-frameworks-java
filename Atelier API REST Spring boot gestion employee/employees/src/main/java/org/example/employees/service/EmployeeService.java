package org.example.employees.service;

import org.example.employees.entities.Employee;

import java.util.List;

public interface EmployeeService  {

    void create(Employee e) ;
    List<Employee> getAll() ;
    Employee getById(int id) ;
    void supprimer(int id) ;
    void update(int id ,Employee e ) ;
}
