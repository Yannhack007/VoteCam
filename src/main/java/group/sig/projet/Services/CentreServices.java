package group.sig.projet.Services;

import group.sig.projet.Models.Admin;
import group.sig.projet.Models.CentreVote;
import group.sig.projet.Models.Resultats;
import group.sig.projet.Repositories.AdminRepository;
import group.sig.projet.Repositories.CentreRepository;
import group.sig.projet.Repositories.ResultatRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CentreServices {
    private CentreRepository centreRepository;
    private AdminRepository adminRepository;
    private ResultatRepository resultatRepository;

    public CentreServices(CentreRepository centreRepository, AdminRepository adminRepository, ResultatRepository resultatRepository) {
        this.centreRepository = centreRepository;
        this.adminRepository = adminRepository;
        this.resultatRepository = resultatRepository;
    }

    public String addCentre(CentreVote centreVote){
        centreVote.setId(UUID.randomUUID());
        centreRepository.save(centreVote);
        return "Centre Saved successfully";
    }

    public String deleteCentre(UUID uuid){
        centreRepository.deleteById(uuid);
        return "Centre deleted successfully";
    }

    public Iterable<CentreVote> getAllCentre(){
        return centreRepository.findAll();
    }

    public CentreVote getCentreById(UUID uuid){
        return centreRepository.findById(uuid).orElse(null);
    }

    public Iterable<Admin> getAllAdminCentreById(UUID uuid){
        CentreVote centreVote=centreRepository.findById(uuid).orElse(null);
        if (centreVote==null){
            throw new IllegalArgumentException("Centre not found");
        }
        return adminRepository.findAllByStructure(uuid);
    }

    public String validateResultats(UUID resultatId){
        Resultats resultats = resultatRepository.findById(resultatId).orElse(null);
        if(resultats==null){
            throw new IllegalArgumentException("Resultats not found");
        }
        // Validation des résultats ici
        resultats.setStatus(Resultats.VoteStatus.SENT);
        resultatRepository.save(resultats);
        // Envoi d'un mail de notification à l'admin responsable du bureau
        return "Resultats validated successfully";
    }
}
