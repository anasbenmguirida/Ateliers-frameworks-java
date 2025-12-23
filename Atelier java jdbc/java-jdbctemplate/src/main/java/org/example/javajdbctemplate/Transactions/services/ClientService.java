package org.example.javajdbctemplate.Transactions.services;

import org.example.javajdbctemplate.Transactions.entities.Client;
import org.example.javajdbctemplate.Transactions.repository.ICRUD;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
@Service
public class ClientService implements ICRUD<Client, Integer> {
    private final JdbcTemplate jdbcTemplate ;

    public ClientService(JdbcTemplate jdbcTemplate){
        System.out.println("creating client service");
        this.jdbcTemplate = jdbcTemplate ;
    }
    @Transactional
    // AOP via proxy design pattern :
    // il ajoute un begin transaction , commit si tous va bien , rollback si une exception
    public void transferMoney(int senderId , int receiverId , float amount) {

            String depot = "update clients set salary  = salary + ? where id =  ? "; // receiver
            jdbcTemplate.update(depot, amount, receiverId);

            String debit = "update clients set salary = salary - ? where id =  ? "; // sender
            jdbcTemplate.update(debit, amount, senderId);


    }




    @Override
    public void create(Client client){
        jdbcTemplate.update("insert into clients values (? , ? , ?)" , client.getId() , client.getName() , client.getSalary());
    }

    @Override
    public void update(Integer integer) {

    }

    @Override
    public List<Client> getAll() {
        return List.of();
    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public Client getById(Integer integer) {
        return null;
    }
}
