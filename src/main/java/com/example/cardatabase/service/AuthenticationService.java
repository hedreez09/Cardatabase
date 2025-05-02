package com.example.cardatabase.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;

import static java.util.Collections.emptyList;

public class AuthenticationService {
    static final long EXPIRATIONTIME = 864_000_00L; // 1 day in milliseconds
    static final String SIGNINGKEY = Base64.getEncoder().encodeToString("V3rYE3cuReK3yMW1thL0tsofR@nd0mCh@rTT0MeetHS512Secur3Req8u1r3m3nt".getBytes());
    static final String PREFIX = "Bearer";

    public static Object addToken(HttpServletResponse res, String username) {
        try{
            Key key = Keys.hmacShaKeyFor(SIGNINGKEY.getBytes(StandardCharsets.UTF_8));
            String JwtToken = Jwts.builder()
                    .setSubject(username)
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                    .signWith(key)
                    .compact();
            res.addHeader("Authorization", PREFIX + " " + JwtToken);
            res.addHeader("Access-Control-Expose-Headers", "Authorization");
            return JwtToken;
        }catch(Exception e){
            e.printStackTrace();
        }
        //Get token from authorization header

        return null;
    }
    public static Authentication getAuthentication(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty()) {
            return null; // No token provided
        }

        // Ensure the token starts with the correct prefix
        if (token.startsWith(PREFIX)) {
            token = token.replace(PREFIX, "").trim();
        } else {
            return null; // Invalid token format
        }

        try {
            // Parse the token and extract the subject (username)
            String user = Jwts.parserBuilder()
                    .setSigningKey(SIGNINGKEY.getBytes(StandardCharsets.UTF_8))
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();

            // Return the authentication object if the user is found
            return user != null ?
                    new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList()) :
                    null;
        } catch (Exception e) {
            e.printStackTrace(); // Log invalid token errors
            return null; // Return null for invalid tokens
        }
    }
}
