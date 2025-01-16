package group.sig.projet.Repositories;

import group.sig.projet.Models.CentreVote;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CentreRepository extends CrudRepository<CentreVote, UUID> {
}
