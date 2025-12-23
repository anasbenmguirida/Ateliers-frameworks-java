package org.example.javajdbctemplate.hibernate.DAO;

import org.example.javajdbctemplate.hibernate.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentDAO extends JpaRepository<Student , Integer> {

}
