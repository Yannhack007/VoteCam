package group.sig.projet.Repositories;

import group.sig.projet.Models.BureauVote;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BureauRepository extends CrudRepository<BureauVote, UUID> {
}
