package group.sig.projet.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
public class Resultats {
    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private UUID bureauId;
    private UUID candidatID;
    private Integer nbVoix;
    private String pvPath;
    private UUID campagneId;

    @Enumerated(EnumType.STRING)
    private VoteStatus status=VoteStatus.PENDING;

    public enum VoteStatus {
        PENDING,    // En attente
        SENT,       // Envoyé pour validation
        VALIDATED,  // Validé par l'admin
        REJECTED    // Rejeté par l'admin
    }
}
