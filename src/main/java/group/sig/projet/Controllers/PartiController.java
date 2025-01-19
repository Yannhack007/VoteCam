package group.sig.projet.Controllers;

import group.sig.projet.Models.Parti;
import group.sig.projet.Services.PartiServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/partis")
public class PartiController {

    private final PartiServices partiServices;

    public PartiController(PartiServices partiServices) {
        this.partiServices = partiServices;
    }

    // 1. Lister tous les partis
    @GetMapping
    public ResponseEntity<Iterable<Parti>> getAllPartis() {
        Iterable<Parti> partis = partiServices.getAllPartis();
        return ResponseEntity.ok(partis);
    }

    // 2. Retrouver un parti par nom
    @GetMapping("/name/{name}")
    public ResponseEntity<Iterable<Parti>> findPartiByName(@PathVariable String name) {
        Iterable<Parti> partis = partiServices.findPartiByName(name);
        return ResponseEntity.ok(partis);
    }

    // 3. Retrouver un parti par nom de candidat
    @GetMapping("/candidate/{candidateName}")
    public ResponseEntity<Iterable<Parti>> findPartiByCandidateName(@PathVariable String candidateName) {
        Iterable<Parti> partis = partiServices.findPartiByCandidateName(candidateName);
        return ResponseEntity.ok(partis);
    }

    // 4. Retrouver un parti par couleur
    @GetMapping("/color/{color}")
    public ResponseEntity<Iterable<Parti>> findPartiByColor(@PathVariable String color) {
        Iterable<Parti> partis = partiServices.findPartiByColor(color);
        return ResponseEntity.ok(partis);
    }

    // 5. Retrouver un parti par ID
    @GetMapping("/{id}")
    public ResponseEntity<Parti> findPartiById(@PathVariable UUID id) {
        Optional<Parti> parti = partiServices.findPartiById(id);
        return parti.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // 6. Ajouter un nouveau parti
    @PostMapping
    public ResponseEntity<String> addParti(@RequestBody Parti parti) {
        String response = partiServices.addParti(parti);
        return ResponseEntity.ok(response);
    }

    // 7. Supprimer un parti
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteParti(@PathVariable UUID id) {
        String response = partiServices.deleteParti(id);
        if (response.equals("Parti introuvable.")) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    // 8. Modifier un parti
    @PutMapping("/{id}")
    public ResponseEntity<String> updateParti(@PathVariable UUID id, @RequestBody Parti updatedParti) {
        String response = partiServices.updateParti(id, updatedParti);
        if (response.equals("Parti introuvable.")) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    // 9. Modifier les informations du candidat d'un parti
    @PatchMapping("/{id}/candidate")
    public ResponseEntity<String> updateCandidateInfo(
            @PathVariable UUID id,
            @RequestParam String candidateName,
            @RequestParam Integer candidateAge,
            @RequestParam String candidateNationality
    ) {
        String response = partiServices.updateCandidateInfo(id, candidateName, candidateAge, candidateNationality);
        if (response.equals("Parti introuvable.")) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }
}
