package group.sig.projet.Controllers;

import group.sig.projet.Models.Campagne;
import group.sig.projet.Services.CampagneServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/campagnes")
public class CampagneController {

    private final CampagneServices campagneServices;

    public CampagneController(CampagneServices campagneServices) {
        this.campagneServices = campagneServices;
    }

    // 1. Ajouter une nouvelle campagne
    @PostMapping
    public ResponseEntity<String> addCampagne(@RequestBody Campagne campagne) {
        String response = campagneServices.addCampagne(campagne);
        return ResponseEntity.ok(response);
    }

    // 2. Supprimer une campagne
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCampagne(@PathVariable UUID id) {
        String response = campagneServices.deleteCampagne(id);
        return ResponseEntity.ok(response);
    }

    // 3. Activer une campagne
    @PutMapping("/{id}/activate")
    public ResponseEntity<String> activateCampagne(@PathVariable UUID id) {
        Campagne campagne = campagneServices.getCampagneById(id);
        if (campagne != null) {
            String response = campagneServices.activateCampagne(campagne);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 4. Désactiver une campagne
    @PutMapping("/{id}/deactivate")
    public ResponseEntity<String> desactivateCampagne(@PathVariable UUID id) {
        Campagne campagne = campagneServices.getCampagneById(id);
        if (campagne != null) {
            String response = campagneServices.desactivateCampagne(campagne);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 5. Récupérer toutes les campagnes
    @GetMapping
    public ResponseEntity<Iterable<Campagne>> getAllCampagne() {
        Iterable<Campagne> campagnes = campagneServices.getAllCampagne();
        return ResponseEntity.ok(campagnes);
    }

    // 6. Récupérer une campagne par ID
    @GetMapping("/{id}")
    public ResponseEntity<Campagne> getCampagneById(@PathVariable UUID id) {
        Campagne campagne = campagneServices.getCampagneById(id);
        if (campagne != null) {
            return ResponseEntity.ok(campagne);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
