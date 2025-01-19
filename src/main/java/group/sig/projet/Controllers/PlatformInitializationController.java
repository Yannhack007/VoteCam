package group.sig.projet.Controllers;

import group.sig.projet.Services.PlatformInitializationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@RestController
@RequestMapping("/initialize")
public class PlatformInitializationController {

    private final PlatformInitializationService initializationService;

    public PlatformInitializationController(PlatformInitializationService initializationService) {
        this.initializationService = initializationService;
    }

    @PostMapping
    public ResponseEntity<String> initializePlatform(
            @RequestParam("file") MultipartFile file,
            @RequestParam("campagneName") String campagneName,
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate
    ) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Fichier JSON manquant.");
        }

        LocalDate startingDate = LocalDate.parse(startDate);
        LocalDate endingDate = LocalDate.parse(endDate);

        String response = initializationService.initializePlatform(file, campagneName, startingDate, endingDate);
        return ResponseEntity.ok(response);
    }
}
