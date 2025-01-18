package group.sig.projet.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
public class Parti {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String candidateName;
    private Integer candidateAge;
    private String candidateNationality;
    private String color;
    private UUID campagneId;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public Integer getCandidateAge() {
        return candidateAge;
    }

    public void setCandidateAge(Integer candidateAge) {
        this.candidateAge = candidateAge;
    }

    public String getCandidateNationality() {
        return candidateNationality;
    }

    public void setCandidateNationality(String candidateNationality) {
        this.candidateNationality = candidateNationality;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public UUID getCampagneId() {
        return campagneId;
    }

    public void setCampagneId(UUID campagneId) {
        this.campagneId = campagneId;
    }
}
