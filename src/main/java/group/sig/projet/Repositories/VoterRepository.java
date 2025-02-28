package group.sig.projet.Repositories;

import group.sig.projet.Models.Voters;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VoterRepository extends CrudRepository<Voters, UUID> {
}
