package org.example.ecole.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "eleve")
public class Eleve {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "matricule", length = 20)
    private String matricule;

    @Column(name = "nom", length = 20)
    private String nom;

    @Column(name = "prenom", length = 20)
    private String prenom;

    @Column(name = "email", length = 30)
    private String email;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_dossier")
    private DossierAdministratif idDossier;

    @ManyToOne
    @JoinColumn(name = "id_filiere")
    private Filiere filiere;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public DossierAdministratif getIdDossier() {
        return idDossier;
    }

    public void setIdDossier(DossierAdministratif idDossier) {
        this.idDossier = idDossier;
    }

    public Filiere getFiliere() {
        return filiere;
    }

    public void setFiliere(Filiere filiere) {
        this.filiere = filiere;
    }

}