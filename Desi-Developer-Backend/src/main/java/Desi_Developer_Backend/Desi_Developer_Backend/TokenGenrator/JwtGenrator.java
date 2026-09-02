package Desi_Developer_Backend.Desi_Developer_Backend.TokenGenrator;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtGenrator {

    private String key = "SwrtGvc464bqK1INe3v5m/FiTZctclHRTmPWMNvF6v2erEpSQe3qh7F4VraVpSsXfuU387Mn3v05D41F1kBj1A==";

    public String JwtGenrateToken(UserDetails userDetails) {

        String token = Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(getSignKey())
                .compact();

        return token;
    }

    private SecretKey getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(key);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Claims getAllClaims(String token){
        JwtParserBuilder parser = Jwts.parser();
        Jws<Claims> signedClaims = parser.verifyWith(getSignKey()).build().parseSignedClaims(token);
        Claims payload = signedClaims.getPayload();
        return payload;
    }

    public boolean isExpired(String token){
        Claims payload = getAllClaims(token);
        Date expiration = payload.getExpiration();
        boolean before = expiration.before(new Date());
        return before;
    }

    public String getUserName(String token){
        Claims payload = getAllClaims(token);
        String userName = payload.getSubject();
        return userName;
    }
}