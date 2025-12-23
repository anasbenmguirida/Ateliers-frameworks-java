package org.example.ecole.service;

import jakarta.persistence.*;
import org.example.ecole.Model.Cour;
import org.example.ecole.Model.CoursFiliere;
import org.example.ecole.Model.CoursFiliereId;
import org.example.ecole.Model.Filiere;
import org.example.ecole.dao.CRUD;

import java.util.ArrayList;
import java.util.List;

public class CoursDAO implements CRUD<Cour , Integer> {

    private final EntityManagerFactory entityManagerFactory ;
    private final EntityManager entityManager ;

    public CoursDAO() {
        entityManagerFactory = Persistence.createEntityManagerFactory("pu_gestion_academique");
        entityManager = entityManagerFactory.createEntityManager() ;
    }
    @Override
    public void create(Cour cour) {

        EntityTransaction transaction = null;

        try {
            transaction= entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(cour);
            transaction.commit();
            System.out.println(" ************ Cour crée avec succée *************");
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
       Cour cour = entityManager.find(Cour.class , id) ;


       if(cour != null){
           System.out.println("**************** id de cours  : " + cour.getId());
           transaction.begin();
           entityManager.remove(cour);
           System.out.println("cour supprimé avec succes **********************");
           transaction.commit();
       }
       else{
           System.out.println("ce cour n'existe pas ");
       }

    }

    @Override
    public void update(Cour cour) {
        EntityTransaction transaction = entityManager.getTransaction();
        Cour existing = this.getById(cour.getId()) ;
        if(existing == null)
        {
            System.out.println("cours existe pas");
            return;
        }
        try{

           transaction.begin();
            entityManager.merge(cour) ;
            transaction.commit();
            System.out.println("mis a jour effectue avec succes ");

        }catch (Exception e){
               transaction.rollback();
           }

    }

    @Override
    public Cour getById(Integer id) {
        Cour cours = entityManager.find(Cour.class , id) ;
        if(cours != null){
            System.out.println("**************** id de cours  : " + cours.getId());
            Cour cour = new Cour() ;
            cour.setId(cours.getId());
            cour.setCode(cours.getCode());
            cour.setIntitule(cours.getIntitule());
            return cour ;
        }
        else{
            System.out.println("ce cour n'existe pas ");
            return null ;
        }
    }

    public Cour getByIdWithFilieres(Integer id) {
        try {
            String jpql = "SELECT c FROM Cour c LEFT JOIN FETCH c.coursFilieres cf LEFT JOIN FETCH cf.filiere WHERE c.id = :id";
            TypedQuery<Cour> query = entityManager.createQuery(jpql, Cour.class);
            query.setParameter("id", id);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            System.err.println("Erreur lors de la récupération du cours avec filières: " + e.getMessage());
            return null;
        }
    }

    // Méthode pour associer des filières à un cours
    public void associateFilieresToCour(Integer coursId, List<Integer> filiereIds) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();

            // Récupérer le cours
            Cour cour = entityManager.find(Cour.class, coursId);
            if (cour == null) {
                throw new IllegalArgumentException("Cours non trouvé avec ID: " + coursId);
            }

            // Supprimer les associations existantes
            String deleteJpql = "DELETE FROM CoursFiliere cf WHERE cf.id.coursId = :coursId";
            Query deleteQuery = entityManager.createQuery(deleteJpql);
            deleteQuery.setParameter("coursId", Long.valueOf(coursId));
            deleteQuery.executeUpdate();

            // Ajouter les nouvelles associations
            for (Integer filiereId : filiereIds) {
                Filiere filiere = entityManager.find(Filiere.class, filiereId);
                if (filiere != null) {
                    CoursFiliere coursFiliere = new CoursFiliere();
                    coursFiliere.setCours(cour);
                    coursFiliere.setFiliere(filiere);
                    coursFiliere.setId(new CoursFiliereId(Integer.valueOf(coursId), Integer.valueOf(filiereId)));

                    entityManager.persist(coursFiliere);
                }
            }

            transaction.commit();
            System.out.println("Filières associées au cours avec succès");

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw new RuntimeException("Erreur lors de l'association des filières", e);
        }
    }

    @Override
    public List<Cour> getAll() {
        try {
            // Essayer d'abord avec JOIN FETCH
            String jpql = "SELECT DISTINCT c FROM Cour c LEFT JOIN FETCH c.coursFilieres cf LEFT JOIN FETCH cf.filiere";
            TypedQuery<Cour> query = entityManager.createQuery(jpql, Cour.class);
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("Erreur avec JOIN FETCH, tentative sans fetch: " + e.getMessage());

            // Fallback: récupérer sans les associations et les charger manuellement
            try {
                String simpleJpql = "FROM Cour";
                TypedQuery<Cour> simpleQuery = entityManager.createQuery(simpleJpql, Cour.class);
                List<Cour> cours = simpleQuery.getResultList();

                // Charger les associations pour chaque cours
                for (Cour cour : cours) {
                    String assocJpql = "SELECT cf FROM CoursFiliere cf JOIN FETCH cf.filiere WHERE cf.cours.id = :coursId";
                    TypedQuery<CoursFiliere> assocQuery = entityManager.createQuery(assocJpql, CoursFiliere.class);
                    assocQuery.setParameter("coursId", cour.getId());
                    List<CoursFiliere> associations = assocQuery.getResultList();
                    cour.setCoursFilieres(associations);
                }

                return cours;
            } catch (Exception e2) {
                System.err.println("Erreur lors de la récupération des cours: " + e2.getMessage());
                return new ArrayList<>();
            }
        }


    }
}
