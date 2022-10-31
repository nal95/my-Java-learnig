package com.nal95.clinic.services;

import org.springframework.security.core.Authentication;

public interface JwtProvider {
    String generateToken(Authentication authentication);
    String generateTokenWithUserName(String username);
    Long getJwtExpirationInMillis();
}
