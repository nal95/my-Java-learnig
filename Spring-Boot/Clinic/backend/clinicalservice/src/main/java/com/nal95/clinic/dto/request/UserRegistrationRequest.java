package com.nal95.clinic.dto.request;

import com.nal95.clinic.model.Role;
import com.nal95.clinic.util.Title;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
public class UserRegistrationRequest {

    private String firstName;

    private String lastName;

    private String username;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Title title;

    private Collection<Role> roles = new ArrayList<>();
}
