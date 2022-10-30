package com.nal95.clinic.model;

import com.nal95.clinic.util.Title;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable=false)
    private String userId;

    private String firstName;

    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    private String encryptedPassword;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Title title;

    private String emailVerificationToken;

    @Column(nullable = false,columnDefinition = "boolean default false")
    private boolean emailVerificationStatus;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>();
}
