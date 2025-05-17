package com.example.cardatabase;

import com.example.cardatabase.service.AuthenticationService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;

import static com.example.cardatabase.service.AuthenticationService.SIGNINGKEY;


@Component
public class JwtUtil {

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    public boolean validateToken(String token) {
        try {
            extractAllClaims(token); // throws if invalid
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private Claims extractAllClaims(String token) {
        Key key = Keys.hmacShaKeyFor(SIGNINGKEY.getBytes(StandardCharsets.UTF_8));
        return Jwts
                .parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
    }
}



