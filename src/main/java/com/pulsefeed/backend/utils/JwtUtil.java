package com.pulsefeed.backend.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "X9v4mTzB2Fq8LrYjWc6nZsQpR3tHkVb5N7dE0aGxU2iP4oC1";
    private final long EXPIRATION_TIME = 1000 * 60 * 60;

    private Key getSigningKey(){
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String generateToken(String email){
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey(),SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractEmail(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean isValidToken(String token){
        try {
            Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token);
            return true;
        }catch (JwtException jwtException){
            return false;
        }
    }

}
