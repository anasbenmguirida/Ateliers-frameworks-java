package org.example.bootjdbc.DAO;

import java.util.List;

public interface ICRUD<T> {

    List<T> getAll() ;
    void create(T t);
    void update(T t) ;


}
