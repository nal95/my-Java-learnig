package com.nal95.clinic.repos;

import com.nal95.clinic.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
        Optional<VerificationToken> findByToken(String token);
        }
