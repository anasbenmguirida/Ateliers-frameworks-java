package org.example.ecole.service;

import jakarta.persistence.*;
import org.example.ecole.Model.Filiere;
import org.example.ecole.dao.CRUD;

import java.util.List;

public class FiliereDAO implements CRUD<Filiere, Integer> {
    private final EntityManagerFactory entityManagerFactory ;
    private final EntityManager entityManager ;

    public FiliereDAO() {
        entityManagerFactory = Persistence.createEntityManagerFactory("pu_gestion_academique");
        entityManager = entityManagerFactory.createEntityManager() ;
    }
    @Override
    public void create(Filiere filiere) {

        EntityTransaction transaction = null;

        try {
            transaction= entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(filiere);
            transaction.commit();
            System.out.println(" ************ filiere crée avec succée *************");
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
        Filiere filiere= entityManager.find(Filiere.class , id) ;


        if(filiere != null){
            System.out.println("**************** id filiere : " + filiere.getId());
            transaction.begin();
            entityManager.remove(filiere);
            System.out.println("filiere supprimé avec succes **********************");
            transaction.commit();
        }
        else{
            System.out.println("filiere n'existe pas ");
        }

    }

    @Override
    public void update( Filiere filiere) {
        EntityTransaction transaction = entityManager.getTransaction();
        try{
            transaction.begin();
            entityManager.merge(filiere) ;
            transaction.commit();
            System.out.println("mis a jour effectue avec succes ");

        }catch (Exception e){
            transaction.rollback();
        }

    }

    @Override
    public Filiere getById(Integer id) {
        Filiere filiere = entityManager.find(Filiere.class , id) ;
        if(filiere != null){
            System.out.println("**************** id filiere : " + filiere.getId());
            return filiere;

        }
        else{
            System.out.println(" filiere n'existe pas ");
            return null ;
        }
    }
    @Override
    public List<Filiere> getAll(){
        EntityTransaction transaction = entityManager.getTransaction() ;
        TypedQuery<Filiere> query = entityManager.createQuery("SELECT f from Filiere f " , Filiere.class) ;
        return query.getResultList() ;

    }

    public static void main(String[] args) {
        Filiere filiere = new Filiere() ;
        filiere.setCode("GINF");
        filiere.setNom("genie informatique");
        filiere.setDescription("formation en informatique couvrant plusieurs domaines metiers");
        new FiliereDAO().create(filiere);
    }
}
