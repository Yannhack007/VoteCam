package group.sig.projet.Repositories;

import group.sig.projet.Models.Parti;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PartiRepository extends CrudRepository<Parti, UUID> {
    Iterable<Parti> findByName(String name);

    // Trouver un parti par candidat
    Iterable<Parti> findByCandidateName(String candidateName);

    // Trouver un parti par couleur
    Iterable<Parti> findByColor(String color);
}
