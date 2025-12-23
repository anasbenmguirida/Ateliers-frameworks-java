package org.example.ecole.Model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "cours")
public class Cour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String code;
    private String intitule;

    // Relation ManyToMany via la classe d'association
    @OneToMany(mappedBy = "cours", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CoursFiliere> coursFilieres = new ArrayList<>(); // Toujours initialiser!

    // Méthode utilitaire pour obtenir les filières
    @Transient
    public List<Filiere> getFilieres() {
        if (coursFilieres == null) {
            return new ArrayList<>(); // Retourner une liste vide au lieu de null
        }
        return coursFilieres.stream()
                .map(CoursFiliere::getFiliere)
                .filter(filiere -> filiere != null) // Filtrer les nulls
                .collect(Collectors.toList());
    }

    // Méthode pour ajouter une filière
    public void addFiliere(Filiere filiere) {
        if (coursFilieres == null) {
            coursFilieres = new ArrayList<>();
        }
        CoursFiliere coursFiliere = new CoursFiliere();
        coursFiliere.setCours(this);
        coursFiliere.setFiliere(filiere);
        coursFiliere.setId(new CoursFiliereId(this.id, filiere.getId()));
        this.coursFilieres.add(coursFiliere);
    }

    // Constructeurs
    public Cour() {
        this.coursFilieres = new ArrayList<>(); // Initialiser dans le constructeur
    }

    // Getters et Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getIntitule() { return intitule; }
    public void setIntitule(String intitule) { this.intitule = intitule; }

    public List<CoursFiliere> getCoursFilieres() {
        if (coursFilieres == null) {
            coursFilieres = new ArrayList<>();
        }
        return coursFilieres;
    }


    public void setCoursFilieres(List<CoursFiliere> coursFilieres) {
        this.coursFilieres = coursFilieres;
    }


    public String toString(){
        return "id : " +this.getId() + "\n" +
                "code : " + this.getCode() +"\n" +
                "intitule  : " + this.getIntitule() ;
    }

}
