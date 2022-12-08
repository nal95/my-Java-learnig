package com.nal95.clinic_v1.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private int age;
    @Column(nullable = false, unique = true)
    private String email;
    @CreationTimestamp
    private Timestamp created;
    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean enabled;
}
