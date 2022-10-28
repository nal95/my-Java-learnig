package com.nal95.clinic.dto.requests;

import com.nal95.clinic.model.Role;
import com.nal95.clinic.utils.Title;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.ArrayList;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDtoRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String encryptedPassword;
    @Enumerated(EnumType.STRING)
    private Title title;
    private String emailVerificationToken;
    private boolean emailVerificationStatus;
    private Collection<Role> roles = new ArrayList<>();
}
