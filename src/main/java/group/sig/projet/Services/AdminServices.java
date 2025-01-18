package group.sig.projet.Services;

import group.sig.projet.Models.Admin;
import group.sig.projet.Repositories.AdminRepository;
import group.sig.projet.Utils.PasswordHasher;
import org.springframework.stereotype.Service;


import java.util.UUID;

@Service
public class AdminServices {
    private PasswordHasher hasher=new PasswordHasher();
    private AdminRepository adminRepository;

    public String addNewAdmin(Admin admin){
        admin.setId(UUID.randomUUID());
        admin.setPassword(hasher.hashPassword(admin.getPassword()));
        adminRepository.save(admin);
        return "Saved";
    }

    public Iterable<Admin> getAllAdmins(){
        return adminRepository.findAll();
    }

    public Admin getAdminById(UUID id){
        return adminRepository.findById(id).orElse(null);
    }

    public String adminLogin(String username,String password){
        Admin admin=adminRepository.findByUsername(username);
        if (admin==null){
            return "User not Exist";
        }

        if(hasher.validatePassword(password,admin.getPassword())){
            return "Login Successful";
        }else {
            return "Incorrect Password";
        }
    }

    public void deleteAdmin(UUID id){
        adminRepository.deleteById(id);
    }


}
