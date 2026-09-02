package Desi_Developer_Backend.Desi_Developer_Backend.Controller;
import Desi_Developer_Backend.Desi_Developer_Backend.DTO.LoginRequest;
import Desi_Developer_Backend.Desi_Developer_Backend.Repositry.AdminRepository;
import Desi_Developer_Backend.Desi_Developer_Backend.TokenGenrator.JwtGenrator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtGenrator jwtGenrator;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                );

        Authentication getAuth =
                authenticationManager.authenticate(authentication);

        UserDetails principal =
                (UserDetails) getAuth.getPrincipal();

        return jwtGenrator.JwtGenrateToken(principal);
    }
}
