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
public class Resultats {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private UUID bureauId;
    private UUID candidatID;
    private Integer nbVoix;
    private String pv;
    private UUID campagneId;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getBureauId() {
        return bureauId;
    }

    public void setBureauId(UUID bureauId) {
        this.bureauId = bureauId;
    }

    public UUID getCandidatID() {
        return candidatID;
    }

    public void setCandidatID(UUID candidatID) {
        this.candidatID = candidatID;
    }

    public Integer getNbVoix() {
        return nbVoix;
    }

    public void setNbVoix(Integer nbVoix) {
        this.nbVoix = nbVoix;
    }

    public String getPv() {
        return pv;
    }

    public void setPv(String pv) {
        this.pv = pv;
    }

    public UUID getCampagneId() {
        return campagneId;
    }

    public void setCampagneId(UUID campagneId) {
        this.campagneId = campagneId;
    }
}
