package group.sig.projet.Services;

import group.sig.projet.Models.Parti;
import group.sig.projet.Repositories.PartiRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PartiServices {
    private PartiRepository partiRepository;

    public PartiServices(PartiRepository partiRepository) {
        this.partiRepository = partiRepository;
    }

    // 1. Lister tous les partis
    public Iterable<Parti> getAllPartis() {
        return partiRepository.findAll();
    }

    // 2. Retrouver un parti à partir de son nom
    public Iterable<Parti> findPartiByName(String name) {
        return partiRepository.findByName(name);
    }

    // 3. Retrouver un parti à partir de son candidat
    public Iterable<Parti> findPartiByCandidateName(String candidateName) {
        return partiRepository.findByCandidateName(candidateName);
    }

    // 4. Retrouver un parti à partir de sa couleur
    public Iterable<Parti> findPartiByColor(String color) {
        return partiRepository.findByColor(color);
    }

    // 5. Retrouver un parti à partir de son ID
    public Optional<Parti> findPartiById(UUID id) {
        return partiRepository.findById(id);
    }

    // 6. Ajouter un nouveau parti
    public String addParti(Parti parti) {
        partiRepository.save(parti);
        return "Parti ajouté avec succès.";
    }

    // 7. Supprimer un parti
    public String deleteParti(UUID id) {
        if (partiRepository.existsById(id)) {
            partiRepository.deleteById(id);
            return "Parti supprimé avec succès.";
        }
        return "Parti introuvable.";
    }

    // 8. Modifier un parti
    public String updateParti(UUID id, Parti updatedParti) {
        Optional<Parti> existingParti = partiRepository.findById(id);

        if (existingParti.isPresent()) {
            Parti parti = existingParti.get();
            parti.setName(updatedParti.getName());
            parti.setCandidateName(updatedParti.getCandidateName());
            parti.setCandidateAge(updatedParti.getCandidateAge());
            parti.setCandidateNationality(updatedParti.getCandidateNationality());
            parti.setColor(updatedParti.getColor());
            parti.setCampagneId(updatedParti.getCampagneId());

            partiRepository.save(parti);
            return "Parti mis à jour avec succès.";
        }

        return "Parti introuvable.";
    }

    // 9. Modifier les informations du candidat d'un parti
    public String updateCandidateInfo(UUID id, String candidateName, Integer candidateAge, String candidateNationality) {
        Optional<Parti> existingParti = partiRepository.findById(id);

        if (existingParti.isPresent()) {
            Parti parti = existingParti.get();
            parti.setCandidateName(candidateName);
            parti.setCandidateAge(candidateAge);
            parti.setCandidateNationality(candidateNationality);

            partiRepository.save(parti);
            return "Informations du candidat mises à jour avec succès.";
        }

        return "Parti introuvable.";
    }
}
