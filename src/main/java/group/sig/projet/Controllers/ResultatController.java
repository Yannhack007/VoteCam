package group.sig.projet.Controllers;

import group.sig.projet.Models.Resultats;
import group.sig.projet.Services.ResultatServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/resultats")
public class ResultatController {

    private final ResultatServices resultatServices;

    public ResultatController(ResultatServices resultatServices) {
        this.resultatServices = resultatServices;
    }

    // 1. Ajouter un résultat avec un fichier PV
    @PostMapping
    public ResponseEntity<String> addResult(
            @ModelAttribute Resultats resultat,
            @RequestParam("pv") MultipartFile pvFile
    ) {
        String response = resultatServices.addResult(resultat, pvFile);
        return ResponseEntity.ok(response);
    }

    // 2. Mettre à jour le statut d'un résultat
    @PutMapping("/{id}/status")
    public ResponseEntity<String> updateVoteStatus(
            @PathVariable UUID id,
            @RequestParam Resultats.VoteStatus status
    ) {
        String response = resultatServices.updateVoteStatus(id, status);
        if (response.equals("Résultat introuvable.")) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    // 3. Récupérer tous les résultats
    @GetMapping
    public ResponseEntity<Iterable<Resultats>> getAllResults() {
        Iterable<Resultats> results = resultatServices.getAllResults();
        return ResponseEntity.ok(results);
    }
}
