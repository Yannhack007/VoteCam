package group.sig.projet.Services;

import group.sig.projet.Models.Admin;
import group.sig.projet.Models.BureauVote;
import group.sig.projet.Repositories.AdminRepository;
import group.sig.projet.Repositories.BureauRepository;

import java.util.UUID;

public class BureauServices {
    private BureauRepository bureauRepository;
    private AdminRepository adminRepository;

    public String addBureau(BureauVote bureauVote){
        bureauVote.setId(UUID.randomUUID());
        bureauRepository.save(bureauVote);
        return "Bureau added successfully";
    }

    public Iterable<BureauVote> getAllBureau(){
        return bureauRepository.findAll();
    }

    public BureauVote getBureauById(UUID id){
        return bureauRepository.findById(id).orElse(null);
    }

    public String deleteBureau(UUID id){
        bureauRepository.deleteById(id);
        return "Bureau deleted successfully";
    }

    public Iterable<Admin> getAllAdminByBureauId(UUID bureauId){
        BureauVote bureau = bureauRepository.findById(bureauId).orElse(null);
        if(bureau==null){
            throw new IllegalArgumentException("Bureau not found");
        }

        return adminRepository.findAllByBureauId(bureauId);
    }
}