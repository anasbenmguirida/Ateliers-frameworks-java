package org.example.ecole.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "cours_filiere")
public class CoursFiliere {

    @EmbeddedId
    private CoursFiliereId id;

    @ManyToOne
    @MapsId("coursId")
    @JoinColumn(name = "id_cours")
    private Cour cours;

    @ManyToOne
    @MapsId("filiereId")
    @JoinColumn(name = "id_filiere")
    private Filiere filiere;


    public CoursFiliere() {}

    public CoursFiliere(Cour cours, Filiere filiere) {
        this.cours = cours;
        this.filiere = filiere;
        this.id = new CoursFiliereId(cours.getId(), filiere.getId());
    }

    // Getters et Setters
    public CoursFiliereId getId() { return id; }
    public void setId(CoursFiliereId id) { this.id = id; }

    public Cour getCours() { return cours; }
    public void setCours(Cour cours) { this.cours = cours; }

    public Filiere getFiliere() { return filiere; }
    public void setFiliere(Filiere filiere) { this.filiere = filiere; }
}