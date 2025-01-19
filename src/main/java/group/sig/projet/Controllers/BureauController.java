package group.sig.projet.Controllers;

import group.sig.projet.Models.Admin;
import group.sig.projet.Models.BureauVote;
import group.sig.projet.Services.BureauServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/bureaux")
public class BureauController {

    private final BureauServices bureauServices;

    public BureauController(BureauServices bureauServices) {
        this.bureauServices = bureauServices;
    }

    // 1. Ajouter un nouveau bureau
    @PostMapping
    public ResponseEntity<String> addBureau(@RequestBody BureauVote bureauVote) {
        String response = bureauServices.addBureau(bureauVote);
        return ResponseEntity.ok(response);
    }

    // 2. Récupérer tous les bureaux
    @GetMapping
    public ResponseEntity<Iterable<BureauVote>> getAllBureau() {
        Iterable<BureauVote> bureaux = bureauServices.getAllBureau();
        return ResponseEntity.ok(bureaux);
    }

    // 3. Récupérer un bureau par ID
    @GetMapping("/{id}")
    public ResponseEntity<BureauVote> getBureauById(@PathVariable UUID id) {
        BureauVote bureau = bureauServices.getBureauById(id);
        if (bureau != null) {
            return ResponseEntity.ok(bureau);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 4. Supprimer un bureau par ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBureau(@PathVariable UUID id) {
        String response = bureauServices.deleteBureau(id);
        return ResponseEntity.ok(response);
    }

    // 5. Récupérer tous les administrateurs d'un bureau
    @GetMapping("/{id}/admins")
    public ResponseEntity<Iterable<Admin>> getAllAdminByBureauId(@PathVariable UUID id) {
        try {
            Iterable<Admin> admins = bureauServices.getAllAdminByBureauId(id);
            return ResponseEntity.ok(admins);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // 6. Valider les résultats
    @PutMapping("/resultats/{resultatId}/validate")
    public ResponseEntity<String> validateResultats(@PathVariable UUID resultatId) {
        try {
            String response = bureauServices.validateResultats(resultatId);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
