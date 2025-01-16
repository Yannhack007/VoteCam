package group.sig.projet.Repositories;

import group.sig.projet.Models.Campagne;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CampagneRepository extends CrudRepository<Campagne, UUID> {
}
