package group.sig.projet.Repositories;

import group.sig.projet.Models.Resultats;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ResultatRepository extends CrudRepository<Resultats, UUID> {
}
