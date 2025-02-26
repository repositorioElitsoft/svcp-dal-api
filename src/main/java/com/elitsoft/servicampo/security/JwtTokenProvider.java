package com.elitsoft.servicampo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.security.core.GrantedAuthority;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}") // Your JWT secret key (keep it secure!)
    public String jwtSecret;

    @Value("${jwt.expiration}") // JWT expiration time in milliseconds
    private long jwtExpirationMs;

    public String generateToken(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal(); // Get user details

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationMs);

        // Extract roles from UserPrincipal
        List<String> roles = userPrincipal.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return Jwts.builder()
                .setSubject(Long.toString(userPrincipal.getId())) // User ID as subject
                .claim("username", userPrincipal.getUsername()) // Add other claims if needed
                .claim("authorities", roles) // Add roles as a claim
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret) // Sign with your secret
                .compact();
    }

    public boolean validateToken(String jwt) { /* ... (Implementation for token validation) */
        try {
            Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(jwt);
            return true; // Token is valid
        } catch (JwtException | IllegalArgumentException e) {
            // Log the exception (helpful for debugging)
            System.err.println("Invalid JWT: " + e.getMessage());
            return false; // Token is invalid
        }
    }

    public Long getUserIdFromJWT(String jwt) { /* ... (Implementation to extract user ID) */
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(jwtSecret)
                .build()
                .parseClaimsJws(jwt)
                .getBody();

        return Long.parseLong(claims.getSubject()); // Assuming user ID is stored in the subject
    }

    // ...
}