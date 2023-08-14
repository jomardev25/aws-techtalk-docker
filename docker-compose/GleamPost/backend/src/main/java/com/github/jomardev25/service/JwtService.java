package com.github.jomardev25.service;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.Claims;

public interface JwtService {
    String getUsernameFromToken(String token);

    String generateToken(UserDetails userDetails);

    String generateToken(Map<String, Object> extraClaims, UserDetails userDetails);

    String generateRefreshToken(UserDetails userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);

    boolean validateToken(String token);

    Date getExpirationDateFromToken(String token);

    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);
}
