package group.sig.projet.Repositories;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface Parti extends CrudRepository<group.sig.projet.Models.Parti, UUID> {
}
