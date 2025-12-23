package org.example.ecole.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "dossier_administratif")
public class DossierAdministratif {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "data_creation")
    private LocalDate dataCreation;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDataCreation() {
        return dataCreation;
    }

    public void setDataCreation(LocalDate dataCreation) {
        this.dataCreation = dataCreation;
    }

}