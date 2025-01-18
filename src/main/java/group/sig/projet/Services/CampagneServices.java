package group.sig.projet.Services;

import group.sig.projet.Models.Campagne;
import group.sig.projet.Repositories.CampagneRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CampagneServices {
    private CampagneRepository campagneRepository;

    public String addCampagne(Campagne campagne){
        campagne.setId(UUID.randomUUID());
        campagneRepository.save(campagne);
        return "SAVED";
    }

    public String deleteCampagne(UUID uuid){
        campagneRepository.deleteById(uuid);
        return "DELETED";
    }

    public String activateCampagne (Campagne campagne){
        campagne.setState("actived");
        campagneRepository.save(campagne);
        return "ACTIVED";
    }

    public String desactivateCampagne (Campagne campagne){
        campagne.setState("desactived");
        campagneRepository.save(campagne);
        return "DESACTIVED";
    }

    public Iterable<Campagne> getAllCampagne(){
        return campagneRepository.findAll();
    }

    public Campagne getCampagneById(UUID uuid){
        return campagneRepository.findById(uuid).orElse(null);
    }
}
