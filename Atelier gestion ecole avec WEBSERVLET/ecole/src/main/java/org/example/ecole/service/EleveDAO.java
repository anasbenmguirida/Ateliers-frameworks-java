package org.example.ecole.service;

import jakarta.persistence.*;
import org.example.ecole.Model.Eleve;
import org.example.ecole.dao.CRUD;

import java.util.List;

public class EleveDAO implements CRUD<Eleve, Integer> {

    private final EntityManagerFactory entityManagerFactory ;
    private final EntityManager entityManager ;
    public EleveDAO() {
        entityManagerFactory = Persistence.createEntityManagerFactory("pu_gestion_academique");
        entityManager = entityManagerFactory.createEntityManager() ;
    }
    @Override
    public void create(Eleve eleve) {

        EntityTransaction transaction = null;

        try {
            transaction= entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(eleve);
            transaction.commit();
            System.out.println(" ************ eleve crée avec succée *************");
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();

        }

    }


    @Override
    public void delete(Integer id) {
        EntityTransaction transaction = entityManager.getTransaction() ;
        Eleve eleve = entityManager.find(Eleve.class , id) ;


        if(eleve != null){
            System.out.println("**************** id eleve  : " + eleve.getId());
            transaction.begin();
            entityManager.remove(eleve);
            System.out.println("c eleve supprimé avec succes **********************");
            transaction.commit();
        }
        else{
            System.out.println("cet eleve n'existe pas ");
        }

    }

    @Override
    public void update(Eleve eleve) {
        EntityTransaction transaction = entityManager.getTransaction();
        try{
            transaction.begin();
            entityManager.merge(eleve) ;
            transaction.commit();
            System.out.println("mis a jour effectue avec succes ");

        }catch (Exception e){
            transaction.rollback();
        }

    }

    @Override
    public Eleve getById(Integer id) {
        Eleve eleve = entityManager.find(Eleve.class , id) ;
        if(eleve != null){
            System.out.println("**************** id de eleve  : " + eleve.getId());
          ;
           return eleve;

        }
        else{
            System.out.println("cet eleve n'existe pas ");
            return null ;
        }
    }
    @Override
    public List<Eleve> getAll(){
        EntityTransaction transaction = entityManager.getTransaction() ;
        TypedQuery<Eleve> query = entityManager.createQuery("SELECT e FROM Eleve e " , Eleve.class) ;
        return query.getResultList() ;

    }

    public static void main(String[] args) {
        System.out.println(new EleveDAO().getAll() ) ;
    }
}
