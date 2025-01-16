package group.sig.projet.Repositories;

import group.sig.projet.Models.BureauVote;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface BureauRepository extends CrudRepository<BureauVote, UUID> {
}
