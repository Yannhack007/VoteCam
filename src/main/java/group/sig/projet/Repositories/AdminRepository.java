package group.sig.projet.Repositories;

import group.sig.projet.Models.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AdminRepository extends CrudRepository<Admin, UUID> {
    Admin findByUsername(String username);
    Iterable<Admin> findAllByStructure(UUID structure);
}
