package group.sig.projet.Services;

import group.sig.projet.Models.Admin;
import group.sig.projet.Models.CentreVote;
import group.sig.projet.Repositories.AdminRepository;
import group.sig.projet.Repositories.CentreRepository;

import java.util.UUID;

public class CentreServices {
    private CentreRepository centreRepository;
    private AdminRepository adminRepository;

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
        return adminRepository.findAllByCentreVoteId(uuid);
    }
}
