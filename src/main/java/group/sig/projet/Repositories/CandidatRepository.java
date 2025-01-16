package group.sig.projet.Repositories;

import group.sig.projet.Models.Candidat;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CandidatRepository extends CrudRepository<Candidat, UUID> {
}
