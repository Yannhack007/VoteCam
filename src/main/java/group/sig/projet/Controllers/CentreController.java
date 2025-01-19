package group.sig.projet.Controllers;

import group.sig.projet.Models.Admin;
import group.sig.projet.Models.CentreVote;
import group.sig.projet.Services.CentreServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/centres")
public class CentreController {

    private final CentreServices centreServices;

    public CentreController(CentreServices centreServices) {
        this.centreServices = centreServices;
    }

    // 1. Ajouter un nouveau centre
    @PostMapping
    public ResponseEntity<String> addCentre(@RequestBody CentreVote centreVote) {
        String response = centreServices.addCentre(centreVote);
        return ResponseEntity.ok(response);
    }

    // 2. Supprimer un centre par ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCentre(@PathVariable UUID id) {
        String response = centreServices.deleteCentre(id);
        return ResponseEntity.ok(response);
    }

    // 3. Récupérer tous les centres
    @GetMapping
    public ResponseEntity<Iterable<CentreVote>> getAllCentre() {
        Iterable<CentreVote> centres = centreServices.getAllCentre();
        return ResponseEntity.ok(centres);
    }

    // 4. Récupérer un centre par ID
    @GetMapping("/{id}")
    public ResponseEntity<CentreVote> getCentreById(@PathVariable UUID id) {
        CentreVote centre = centreServices.getCentreById(id);
        if (centre != null) {
            return ResponseEntity.ok(centre);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 5. Récupérer tous les administrateurs d'un centre par ID
    @GetMapping("/{id}/admins")
    public ResponseEntity<Iterable<Admin>> getAllAdminCentreById(@PathVariable UUID id) {
        try {
            Iterable<Admin> admins = centreServices.getAllAdminCentreById(id);
            return ResponseEntity.ok(admins);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // 6. Valider les résultats pour un centre
    @PutMapping("/resultats/{resultatId}/validate")
    public ResponseEntity<String> validateResultats(@PathVariable UUID resultatId) {
        try {
            String response = centreServices.validateResultats(resultatId);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
