package group.sig.projet.Controllers;

import group.sig.projet.Services.VoterServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/voters")
public class VoterController {

    private final VoterServices voterServices;

    public VoterController(VoterServices voterServices) {
        this.voterServices = voterServices;
    }

    // 1. Importer un fichier de données des électeurs
    @PostMapping("/upload")
    public ResponseEntity<String> uploadVotersFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Le fichier est vide.");
        }

        String response = voterServices.uploadVotersFile(file);
        return ResponseEntity.ok(response);
    }
}
