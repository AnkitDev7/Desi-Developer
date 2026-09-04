package Desi_Developer_Backend.Desi_Developer_Backend.JwtFilter;

import Desi_Developer_Backend.Desi_Developer_Backend.TokenGenrator.JwtGenrator;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTFilter extends OncePerRequestFilter {

    private final JwtGenrator jwtGenrator;
    private final UserDetailsService userDetailsService;

    public JWTFilter(
            JwtGenrator jwtGenrator,
            UserDetailsService userDetailsService
    ) {
        this.jwtGenrator = jwtGenrator;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        // JWT nahi hai
        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.substring(7);

        try {

            // JWT se email
            String username = jwtGenrator.getUserName(token);

            System.out.println("JWT Username: " + username);

            // Database se admin/user details
            UserDetails userDetails =
                    userDetailsService.loadUserByUsername(username);

            // Authentication object
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );

            // SecurityContext mein authentication set
            SecurityContextHolder
                    .getContext()
                    .setAuthentication(authentication);

            System.out.println("JWT Authentication Success");
            System.out.println(
                    "Authorities: " + userDetails.getAuthorities()
            );

        } catch (Exception e) {

            System.out.println(
                    "JWT Authentication Failed: " + e.getMessage()
            );

        }

        filterChain.doFilter(request, response);
    }
}