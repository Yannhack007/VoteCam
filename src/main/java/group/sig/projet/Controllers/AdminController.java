package group.sig.projet.Controllers;

import group.sig.projet.Models.Admin;
import group.sig.projet.Services.AdminServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/admins")
public class AdminController {

    private final AdminServices adminServices;

    public AdminController(AdminServices adminServices) {
        this.adminServices = adminServices;
    }

    // 1. Ajouter un nouvel administrateur
    @PostMapping("/add")
    public ResponseEntity<String> addNewAdmin(@RequestBody Admin admin) {
        String response = adminServices.addNewAdmin(admin);
        return ResponseEntity.ok(response);
    }

    // 2. Récupérer tous les administrateurs
    @GetMapping
    public ResponseEntity<Iterable<Admin>> getAllAdmins() {
        Iterable<Admin> admins = adminServices.getAllAdmins();
        return ResponseEntity.ok(admins);
    }

    // 3. Récupérer un administrateur par ID
    @GetMapping("/{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable UUID id) {
        Admin admin = adminServices.getAdminById(id);
        if (admin != null) {
            return ResponseEntity.ok(admin);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 4. Connexion administrateur
    @PostMapping("/login")
    public ResponseEntity<String> adminLogin(@RequestParam String username, @RequestParam String password) {
        String response = adminServices.adminLogin(username, password);
        if (response.equals("Login Successful")) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }

    // 5. Supprimer un administrateur par ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAdmin(@PathVariable UUID id) {
        adminServices.deleteAdmin(id);
        return ResponseEntity.ok("Admin supprimé avec succès.");
    }
}
