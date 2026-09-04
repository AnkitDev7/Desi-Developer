package Desi_Developer_Backend.Desi_Developer_Backend.Service;

import Desi_Developer_Backend.Desi_Developer_Backend.Entity.Admin;
import Desi_Developer_Backend.Desi_Developer_Backend.Repositry.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AdminDetails implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        Admin admin = adminRepository.findByEmail(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Admin not found")
                );

        return User.builder()
                .username(admin.getEmail())
                .password(admin.getPassword())
                .roles("ADMIN")
                .build();
    }
}