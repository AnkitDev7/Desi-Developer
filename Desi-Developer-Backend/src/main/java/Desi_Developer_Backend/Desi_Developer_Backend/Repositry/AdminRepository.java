package Desi_Developer_Backend.Desi_Developer_Backend.Repositry;
import Desi_Developer_Backend.Desi_Developer_Backend.Entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    Optional<Admin> findByEmail(String email);
}
