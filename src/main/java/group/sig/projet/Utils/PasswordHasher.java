package group.sig.projet.Utils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordHasher {
    private final BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    public String hashPassword(String pass){
        return passwordEncoder.encode(pass);
    }

    public boolean validatePassword(String pass, String hash){
        return passwordEncoder.matches(pass,hash);
    }
}
