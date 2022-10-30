package com.nal95.clinic.dto.responses;

import com.nal95.clinic.utils.Title;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class LoginResponse {
    private String userId;

    private String firstName;

    private String lastName;

    private String email;

    @Enumerated(EnumType.STRING)
    private Title title;

    private boolean emailVerificationStatus;
}
