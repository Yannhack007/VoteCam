package group.sig.projet.Services;

import group.sig.projet.Models.Resultats;
import group.sig.projet.Repositories.ResultatRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Optional;
import java.util.UUID;

@Service
public class ResultatServices{
    private final ResultatRepository resultatRepository;

    public ResultatServices(ResultatRepository resultatRepository){
        this.resultatRepository=resultatRepository;
    }

    public String addResult(Resultats resultat, MultipartFile pvFile) {
        try {
            // Enregistrer le fichier PV
            String filePath = savePVFile(pvFile);
            resultat.setPvPath(filePath);
            resultat.setStatus(Resultats.VoteStatus.VALIDATED); // Mettre le statut sur "SENT"
            resultatRepository.save(resultat);
            return "Résultat ajouté avec succès et fichier PV téléversé.";
        } catch (Exception e) {
            return "Erreur lors de l'ajout du résultat : " + e.getMessage();
        }
    }

    // Sauvegarder un fichier PV
    private String savePVFile(MultipartFile file) throws Exception {
        String uploadDir = "uploads/pv/"; // Dossier pour stocker les PV
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            directory.mkdirs(); // Créer le dossier si nécessaire
        }

        String filePath = uploadDir + UUID.randomUUID() + "_" + file.getOriginalFilename();
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            fos.write(file.getBytes());
        }
        return filePath;
    }

    // Mettre à jour le statut d'un résultat
    public String updateVoteStatus(UUID resultatId, Resultats.VoteStatus newStatus) {
        Optional<Resultats> optionalResultat = resultatRepository.findById(resultatId);
        if (optionalResultat.isPresent()) {
            Resultats resultat = optionalResultat.get();
            resultat.setStatus(newStatus);
            resultatRepository.save(resultat);
            return "Statut du résultat mis à jour : " + newStatus;
        }
        return "Résultat introuvable.";
    }

    public Iterable<Resultats> getAllResults(){
        return resultatRepository.findAll();
    }


}
