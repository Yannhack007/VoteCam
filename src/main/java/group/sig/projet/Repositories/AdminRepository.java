package group.sig.projet.Repositories;

import group.sig.projet.Models.Admin;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AdminRepository extends CrudRepository<Admin, UUID> {
}
