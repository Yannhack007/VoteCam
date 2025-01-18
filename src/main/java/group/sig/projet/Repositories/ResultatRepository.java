    package group.sig.projet.Repositories;

import group.sig.projet.Models.Resultats;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ResultatRepository extends CrudRepository<Resultats, UUID> {
    List<Resultats> findByBureauId(UUID bureauId);
    List<Resultats> findByCandidatID(UUID candidatId);
    List<Resultats> findByStatus (Resultats.VoteStatus status);
}
