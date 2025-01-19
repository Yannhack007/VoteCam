package group.sig.projet.Services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import group.sig.projet.Models.Admin;
import group.sig.projet.Models.BureauVote;
import group.sig.projet.Models.Campagne;
import group.sig.projet.Models.CentreVote;
import group.sig.projet.Repositories.AdminRepository;
import group.sig.projet.Repositories.BureauRepository;
import group.sig.projet.Repositories.CampagneRepository;
import group.sig.projet.Repositories.CentreRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PlatformInitializationService {

    private final BureauRepository bureauRepository;
    private final CentreRepository centreRepository;
    private final AdminRepository adminRepository;
    private final CampagneRepository campagneRepository;

    public PlatformInitializationService(
            BureauRepository bureauRepository,
            CentreRepository centreRepository,
            AdminRepository adminRepository,
            CampagneRepository campagneRepository) {
        this.bureauRepository = bureauRepository;
        this.centreRepository = centreRepository;
        this.adminRepository = adminRepository;
        this.campagneRepository = campagneRepository;
    }

    public String initializePlatform(MultipartFile file, String campagneName, LocalDate startDate, LocalDate endDate) {
        try {
            // Créer la campagne
            Campagne campagne = new Campagne();
            campagne.setId(UUID.randomUUID());
            campagne.setName(campagneName);
            campagne.setStartingDate(startDate);
            campagne.setEndingDate(endDate);
            campagne.setState("En cours");
            campagneRepository.save(campagne);

            // Lire et désérialiser le fichier JSON
            ObjectMapper mapper = new ObjectMapper();
            List<Region> regions = mapper.readValue(file.getInputStream(), new TypeReference<>() {});

            // Traiter chaque région
            for (Region region : regions) {
                for (Department department : region.getDepartments()) {
                    // Créer un centre de vote pour chaque département
                    CentreVote centreVote = new CentreVote();
                    centreVote.setId(UUID.randomUUID());
                    centreVote.setName(department.getDepartmentName());
                    centreVote.setRegion(region.getRegionName());
                    centreVote.setCountry("Cameroon");
                    centreVote.setCampagneId(campagne.getId());
                    centreRepository.save(centreVote);

                    // Créer un admin pour ce centre
                    createAdmin(centreVote.getId(), "centre_" + department.getDepartmentName());

                    // Créer des bureaux de vote pour chaque arrondissement
                    for (Arrondissement arrondissement : department.getArrondissements()) {
                        BureauVote bureauVote = new BureauVote();
                        bureauVote.setId(UUID.randomUUID());
                        bureauVote.setName(arrondissement.getName());
                        bureauVote.setArrondissement(arrondissement.getName());
                        bureauVote.setCentreId(centreVote.getId());
                        bureauVote.setCampagneId(campagne.getId());
                        bureauRepository.save(bureauVote);

                        // Créer un admin pour ce bureau
                        createAdmin(bureauVote.getId(), "bureau_" + arrondissement.getName());
                    }
                }
            }

            return "Initialisation terminée avec succès.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erreur lors de l'initialisation de la plateforme : " + e.getMessage();
        }
    }

    private void createAdmin(UUID structureId, String usernamePrefix) {
        Admin admin = new Admin();
        admin.setId(UUID.randomUUID());
        admin.setUsername(usernamePrefix + "_admin");
        admin.setPassword("12345"); // À hacher pour la sécurité
        admin.setStructure(structureId);
        adminRepository.save(admin);
    }

    // Classes internes pour le JSON
    static class Region {
        private String regionName;
        private List<Department> departments;

        // Getters et setters
        public String getRegionName() { return regionName; }
        public void setRegionName(String regionName) { this.regionName = regionName; }
        public List<Department> getDepartments() { return departments; }
        public void setDepartments(List<Department> departments) { this.departments = departments; }
    }

    static class Department {
        private String departmentName;
        private List<Arrondissement> arrondissements;

        // Getters et setters
        public String getDepartmentName() { return departmentName; }
        public void setDepartmentName(String departmentName) { this.departmentName = departmentName; }
        public List<Arrondissement> getArrondissements() { return arrondissements; }
        public void setArrondissements(List<Arrondissement> arrondissements) { this.arrondissements = arrondissements; }
    }

    static class Arrondissement {
        private String name;

        // Getters et setters
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
    }
}
