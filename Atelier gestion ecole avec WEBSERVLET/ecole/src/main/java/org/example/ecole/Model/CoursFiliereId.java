package org.example.ecole.Model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CoursFiliereId implements Serializable {

    private int coursId;
    private int filiereId;

    // Constructeurs
    public CoursFiliereId() {}

    public CoursFiliereId(int coursId, int filiereId) {
        this.coursId = coursId;
        this.filiereId = filiereId;
    }

    // Getters et Setters
    public int getCoursId() { return coursId; }
    public void setCoursId(int coursId) { this.coursId = coursId; }

    public  int getFiliereId() { return filiereId; }
    public void setFiliereId(int filiereId) { this.filiereId = filiereId; }

    // equals() et hashCode() obligatoires pour les clés composites
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoursFiliereId that = (CoursFiliereId) o;
        return Objects.equals(coursId, that.coursId) &&
                Objects.equals(filiereId, that.filiereId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coursId, filiereId);
    }
}