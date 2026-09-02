package Desi_Developer_Backend.Desi_Developer_Backend.Service;
import Desi_Developer_Backend.Desi_Developer_Backend.Entity.Admin;
import Desi_Developer_Backend.Desi_Developer_Backend.Repositry.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminDetails implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Admin> byEmail = adminRepository.findByEmail(username);

        Admin admin = null;

        if (byEmail.isPresent()) {
            admin = byEmail.get();
        }else{
            throw new RuntimeException("Admin not found");
        }

        UserDetails userDetails =  User.builder()
                .username(admin.getEmail())
                .password(admin.getPassword())
                .build();

        return userDetails;
    }
}
