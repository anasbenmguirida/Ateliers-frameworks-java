package org.example.javajdbctemplate.Transactions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ICRUD<T , PK> {
    void create(T t) ;
    void update(PK pk) ;
    List<T> getAll() ;
    void delete(PK pk ) ;
    T getById(PK pk) ;
}
