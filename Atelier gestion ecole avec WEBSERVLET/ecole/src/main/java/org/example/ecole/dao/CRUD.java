package org.example.ecole.dao;

import java.util.List;

public interface CRUD <T , pk>{

    void create(T t ) ;
    void delete(pk id) ;
    void update(T t) ;
    T getById(pk id) ;
    List<T> getAll() ;

}
