package org.example.employees.service;

import lombok.AllArgsConstructor;
import org.example.employees.entities.Employee;
import org.example.employees.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeServiceImp implements EmployeeService{

    private final EmployeeRepository repository ;
    @Override
    public void create(Employee e) {
        repository.save(e) ;
    }

    @Override
    public List<Employee> getAll() {
        return repository.findAll()  ;
    }

    @Override
    public Employee getById(int id) {
        return repository.findById(id).orElse(null) ;
    }

    @Override
    public void update(int id ,Employee e ) {
        Employee employee = getById(id) ;
        if(employee!=null){
            employee.setSalaire(e.getSalaire());
            employee.setDateEntree(e.getDateEntree());
            employee.setNomComplet(e.getNomComplet());
            repository.save(employee);
        }
        else{
            System.out.println("employee avec cette id n'existe pas");
            return;
        }

    }

    @Override
    public void supprimer(int id) {
        try{
            Employee employee = getById(id) ;
            repository.delete(employee) ;
        }catch(Exception e){
            System.out.println("l'employee nexiste pas ");
        }
    }


}
