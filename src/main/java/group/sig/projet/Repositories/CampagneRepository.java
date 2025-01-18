package group.sig.projet.Repositories;

import group.sig.projet.Models.Campagne;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CampagneRepository extends CrudRepository<Campagne, UUID> {
}
