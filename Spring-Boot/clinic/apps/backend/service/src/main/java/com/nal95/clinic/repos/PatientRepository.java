package com.nal95.clinic.repos;

import com.nal95.clinic.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findUserByEmail(String email);
}
