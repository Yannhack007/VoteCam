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
public class BureauVote {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String arrondissement;
    private UUID centreId;
    private UUID adminId;
    private UUID campagneId;
    private Integer nbElecteurs;

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

    public String getArrondissement() {
        return arrondissement;
    }

    public void setArrondissement(String arrondissement) {
        this.arrondissement = arrondissement;
    }

    public UUID getCentreId() {
        return centreId;
    }

    public void setCentreId(UUID centreId) {
        this.centreId = centreId;
    }

    public UUID getAdminId() {
        return adminId;
    }

    public void setAdminId(UUID adminId) {
        this.adminId = adminId;
    }

    public UUID getCampagneId() {
        return campagneId;
    }

    public void setCampagneId(UUID campagneId) {
        this.campagneId = campagneId;
    }

    public Integer getNbElecteurs() {
        return nbElecteurs;
    }

    public void setNbElecteurs(Integer nbElecteurs) {
        this.nbElecteurs = nbElecteurs;
    }
}
